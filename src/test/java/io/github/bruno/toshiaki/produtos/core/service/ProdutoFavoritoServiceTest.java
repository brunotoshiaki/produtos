package io.github.bruno.toshiaki.produtos.core.service;

import io.github.bruno.toshiaki.produtos.core.exeption.ProdutoAlreadyRegisteredExeption;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoDTO;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoFavoritoRepository;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoRepository;
import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import io.github.bruno.toshiaki.produtos.output.database.model.ProdutoFavorito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoFavoritoServiceTest {


    @Mock
    private ProdutoService produtoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoFavoritoRepository produtoFavoritoRepository;

    @InjectMocks
    private ProdutoFavoritoService produtoFavoritoService;

    @Test
    void shouldAdd() {
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setNome("nome");
        cliente.setId(1L);
        var produto = new Produto();
        produto.setPrice(10.0);
        produto.setImage("image.jpg");
        produto.setBrand("lala");
        produto.setTitle("Sabonete");
        produto.setReviewScore(6.0);
        var produtoFavorito = new ProdutoFavorito();
        produtoFavorito.setId(1L);
        produtoFavorito.getProdutos().add(produto);
        when(produtoService.buscarPorId(any())).thenReturn(produto);
        when(clienteService.buscarPorId(any())).thenReturn(cliente);
        produtoFavoritoService.adicionarCarrinho(new ProdutoFavoritoDTO(1L, 1L));
        when(produtoRepository.findProdutoByIdAndIdCliente(any(),any())).thenReturn(Optional.empty());
        verify(produtoFavoritoRepository,times(1)).save(any(ProdutoFavorito.class));
        assertDoesNotThrow(() -> produtoFavoritoService.adicionarCarrinho(new ProdutoFavoritoDTO(1L, 1L)));
    }

    @Test
    void shouldThrowProdutoAlreadyRegisteredExeptionWhenProdutoAlreadyInCart() {
        var cliente = new Cliente();
        cliente.setId(1L);

        var produtoFavorito = new ProdutoFavorito();
        produtoFavorito.setCliente(cliente);
        var produto = new Produto();
        produto.setPrice(10.0);
        produto.setImage("image.jpg");
        produto.setBrand("lala");
        produto.setTitle("Sabonete");
        produto.setReviewScore(6.0);
        when(clienteService.buscarPorId(1L)).thenReturn(cliente);
        when(produtoService.buscarPorId(1L)).thenReturn(produto);
        when(produtoRepository.findProdutoByIdAndIdCliente(any(),any())).thenReturn(Optional.of(produto));
        assertThrows(ProdutoAlreadyRegisteredExeption.class, () -> produtoFavoritoService.adicionarCarrinho(new ProdutoFavoritoDTO(1L, 1L)));
    }

}