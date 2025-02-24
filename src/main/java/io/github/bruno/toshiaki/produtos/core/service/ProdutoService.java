package io.github.bruno.toshiaki.produtos.core.service;

import io.github.bruno.toshiaki.produtos.core.exeption.ProdutoNotFoundExeption;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoDTO;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoResponse;
import io.github.bruno.toshiaki.produtos.mapper.ProdutoMapper;
import io.github.bruno.toshiaki.produtos.mapper.ProdutoResponseMapper;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoRepository;
import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final ProdutoResponseMapper produtoResponseMapper;


    public void salvar(ProdutoDTO dto) {
        produtoRepository.save(produtoMapper.fromDTO(dto));
    }

    public Produto buscarPorId(Long id) {
        return
                produtoRepository
                        .findById(id)
                        .orElseThrow(ProdutoNotFoundExeption::new);
    }

    public Page<ProdutoResponse> buscarPaginada(int pagina) {
        var pageable = PageRequest.of(pagina, 10);
        return produtoRepository.findAll(pageable).map(produtoResponseMapper::fromEntity);
    }


    public void atualizar(Long id, ProdutoDTO dto) {
        var produto = produtoMapper.fromDTO(dto);
        var produtoEncontrado = buscarPorId(id);
        produto.setId(produtoEncontrado.getId());
        produto.setProdutoFavorito(produtoEncontrado.getProdutoFavorito());
        produtoRepository.save(produto);
    }


}
