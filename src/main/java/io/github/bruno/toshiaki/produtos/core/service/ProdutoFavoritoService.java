package io.github.bruno.toshiaki.produtos.core.service;

import io.github.bruno.toshiaki.produtos.core.exeption.ProdutoAlreadyRegisteredExeption;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoDTO;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoFavoritoRepository;
import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import io.github.bruno.toshiaki.produtos.output.database.model.ProdutoFavorito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoFavoritoService {

    private final ProdutoFavoritoRepository produtoFavoritoRepository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;

    public void adicionarCarrinho(ProdutoFavoritoDTO favoritoDTO) {
        var cliente = clienteService.buscarPorId(favoritoDTO.idCliente());
        adicionaCarrinho(cliente, favoritoDTO);
    }

    private void adicionaCarrinho(Cliente cliente, ProdutoFavoritoDTO favoritoDTO) {
        var produtoFavorito = new ProdutoFavorito();
        produtoFavorito.setCliente(cliente);

        var produtoCadastrado = produtoFavoritoRepository.findByCliente(cliente);
        var produto = produtoService.buscarPorId(favoritoDTO.idProduto());
        if (produtoCadastrado.isPresent()) {
            throw new ProdutoAlreadyRegisteredExeption();
        } else {
            produtoFavorito.getProdutos().add(produto);
            produtoFavoritoRepository.save(produtoFavorito);
        }
    }



}
