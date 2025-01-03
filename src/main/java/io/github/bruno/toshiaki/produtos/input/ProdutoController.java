package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoDTO;
import io.github.bruno.toshiaki.produtos.core.service.ProdutoService;
import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody ProdutoDTO produto) {
        produtoService.salvar(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable String id) {
        var produto = produtoService.buscarPorId(Long.parseLong(id));
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> buscaPaginada(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        var result = produtoService.buscarPaginada(page);
        return ResponseEntity.ok().body(result);
    }
}
