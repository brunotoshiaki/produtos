package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoDTO;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoResponse;
import io.github.bruno.toshiaki.produtos.core.service.ProdutoService;
import io.github.bruno.toshiaki.produtos.mapper.ProdutoResponseMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoResponseMapper produtoResponseMapper;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody ProdutoDTO produto) {
        produtoService.salvar(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> detalhe(@PathVariable("id") Long id) {
        var produto = produtoService.buscarPorId(id);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{title}")
                .path("/{image}")
                .path("/{price}")
                .path("/{id}")
                .buildAndExpand(produto.getTitle(), produto.getImage(), produto.getPrice(), produto.getId()).toUri();

        return ResponseEntity.created(uri).body(produtoResponseMapper.fromEntity(produto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProdutoResponse>> listagem(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        var result = produtoService.buscarPaginada(page);
        return ResponseEntity.ok().body(result.getContent());
    }
}
