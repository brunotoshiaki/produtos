package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoDTO;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoResponse;
import io.github.bruno.toshiaki.produtos.core.service.ProdutoFavoritoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtoFavorito")
@RequiredArgsConstructor
@Tag(name = "ProdutoFavorito")
public class ProdutoFavoritoController {

    private final ProdutoFavoritoService produtoFavoritoService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody ProdutoFavoritoDTO produto) {
        produtoFavoritoService.adicionarCarrinho(produto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}/{idProduto}")
    public ResponseEntity<Void> remover(@PathVariable Long id, @PathVariable Long idProduto) {
        produtoFavoritoService.removeCarrinho(id,idProduto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoFavoritoResponse> listar(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                          @PathVariable("id") Long id) {

        var result = produtoFavoritoService.listagem(page, size, id);

        return ResponseEntity.ok().body(result);
    }

}
