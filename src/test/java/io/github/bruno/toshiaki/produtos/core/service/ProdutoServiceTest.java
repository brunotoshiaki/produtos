package io.github.bruno.toshiaki.produtos.core.service;

import io.github.bruno.toshiaki.produtos.core.exeption.ProdutoNotFoundExeption;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoDTO;
import io.github.bruno.toshiaki.produtos.mapper.ProdutoMapper;
import io.github.bruno.toshiaki.produtos.mapper.ProdutoResponseMapper;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoRepository;
import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoResponseMapper produtoResponseMapper;

    @Test
   void shouldSaveProdut(){
       var dto = new ProdutoDTO(10.0,"image.jpg","Lala","Sabonete",6.0);
       var produto = new Produto();
       produto.setPrice(10.0);
       produto.setImage("image.jpg");
       produto.setBrand("lala");
       produto.setTitle("Sabonete");
       produto.setReviewScore(6.0);
       when(produtoMapper.fromDTO(dto)).thenReturn(produto);
       produtoRepository.save(produto);
        verify(produtoRepository, times(1)).save(produto);
        assertDoesNotThrow(() -> produtoService.salvar(dto));
    }

    @Test
    void shouldFindProdut(){
        var produto = new Produto();
        produto.setPrice(10.0);
        produto.setImage("image.jpg");
        produto.setBrand("lala");
        produto.setTitle("Sabonete");
        produto.setReviewScore(6.0);
        when(produtoRepository.findById(produto.getId())).thenReturn(Optional.of(produto));
        assertDoesNotThrow(() -> produtoService.buscarPorId(produto.getId()));
    }

    @Test
    void shouldThrowExceptionWhenDontFindProdut(){
        when(produtoRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ProdutoNotFoundExeption.class, () -> produtoService.buscarPorId(1L));
    }


    @Test
    void shouldFindAllProdutos() {
        var produto = new Produto();
        produto.setPrice(10.0);
        produto.setImage("image.jpg");
        produto.setBrand("lala");
        produto.setTitle("Sabonete");
        produto.setReviewScore(6.0);
        var pageable = PageRequest.of(1, 10);
        when(produtoRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(produto)));
        var result = produtoService.buscarPaginada(1);
        assertNotNull(result);
        verify(produtoRepository, times(1)).findAll(pageable);
    }

}