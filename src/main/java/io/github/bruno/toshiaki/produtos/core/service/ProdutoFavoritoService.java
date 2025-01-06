package io.github.bruno.toshiaki.produtos.core.service;

import io.github.bruno.toshiaki.produtos.core.exeption.ProdutoAlreadyRegisteredExeption;
import io.github.bruno.toshiaki.produtos.core.exeption.ProdutoNotFoundExeption;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoDTO;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoResponse;
import io.github.bruno.toshiaki.produtos.mapper.ClienteMapper;
import io.github.bruno.toshiaki.produtos.mapper.ProdutoResponseMapper;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoFavoritoRepository;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoRepository;
import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import io.github.bruno.toshiaki.produtos.output.database.model.ProdutoFavorito;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoFavoritoService {

    private final ProdutoFavoritoRepository produtoFavoritoRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;
    private final ProdutoResponseMapper produtoResponseMapper;

    public void adicionarCarrinho(ProdutoFavoritoDTO favoritoDTO) {
        var cliente = clienteService.buscarPorId(favoritoDTO.idCliente());
        adicionaCarrinho(cliente, favoritoDTO);
    }

    private void adicionaCarrinho(Cliente cliente, ProdutoFavoritoDTO favoritoDTO) {
        var produto = produtoService.buscarPorId(favoritoDTO.idProduto());
        var produtoFavorito = new ProdutoFavorito();
        var produtoCadastrado = produtoRepository.findProdutoById(favoritoDTO.idProduto());

        if (produtoCadastrado.isPresent()) {
            throw new ProdutoAlreadyRegisteredExeption();
        } else {
            var produtoFavoritoEncontrado = produtoFavoritoRepository.findByCliente(cliente);
            produtoFavoritoEncontrado.ifPresent(favorito -> produtoFavorito.setId(favorito.getId()));
            //novo
            produtoFavorito.setCliente(cliente);
            produtoFavorito.getProdutos().add(produto);
            produtoFavoritoRepository.save(produtoFavorito);
            produto.setProdutoFavorito(produtoFavorito);
            produtoRepository.save(produto);
        }
    }

    public ProdutoFavoritoResponse listagem(Integer page, Integer size, Long idCliente) {
        var cliente = clienteService.buscarPorId(idCliente);
        var pageable = PageRequest.of(page, size);
        var produtoFavoritos = produtoFavoritoRepository.findAllByCliente(idCliente, pageable).getContent();
        var produtoResponse = produtoFavoritos.stream().map(produtoResponseMapper::fromEntity).collect(Collectors.toSet());

        return new ProdutoFavoritoResponse(clienteMapper.toDTO(cliente), produtoResponse);
    }


    public void removeCarrinho(Long id, Long idProduto) {
        var produtoFavorito = produtoFavoritoRepository.findById(id).orElseThrow(ProdutoNotFoundExeption::new);

        var produto = produtoService.buscarPorId(idProduto);
        produtoFavorito.getProdutos().remove(produto);

        produtoFavoritoRepository.save(produtoFavorito);
        produto.setProdutoFavorito(null);
        produtoRepository.save(produto);

    }
}
