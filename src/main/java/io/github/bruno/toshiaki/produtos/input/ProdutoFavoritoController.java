package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoDTO;
import io.github.bruno.toshiaki.produtos.core.service.ProdutoFavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtoFavorito")
@RequiredArgsConstructor
public class ProdutoFavoritoController {

    private final ProdutoFavoritoService produtoFavoritoService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody ProdutoFavoritoDTO produto) {
        produtoFavoritoService.adicionarCarrinho(produto);
        return ResponseEntity.ok().build();
    }
}
