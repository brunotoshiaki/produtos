package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoDTO;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoResponse;
import io.github.bruno.toshiaki.produtos.core.service.ProdutoService;
import io.github.bruno.toshiaki.produtos.mapper.ProdutoResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Operation(summary = "Cadastrar", description = "Cadastra um Produto")
    @ApiResponse(responseCode = "200", description = "Produto Cadastrado com Sucesso.")
    public ResponseEntity<Void> cadastrar(@RequestBody ProdutoDTO produto) {
        produtoService.salvar(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalha", description = "Detalha  um Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Detalhado."),
            @ApiResponse(responseCode = "404", description = "Produto nao encontrado.")
    })
    public ResponseEntity<ProdutoResponse> detalhe(@PathVariable("id") Long id) {
        var produto = produtoService.buscarPorId(id);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{title}")
                .path("/{image}")
                .path("/{price}")
                .buildAndExpand(produto.getTitle(), produto.getImage(), produto.getPrice()).toUri();
        var response = produtoResponseMapper.fromEntity(produto);

        response.add(Link.of(uri.toString()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    @Operation(summary = "Listar", description = "Lista todos os Produtos por página")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<ProdutoResponse>> listagem(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        var result = produtoService.buscarPaginada(page);
        return ResponseEntity.ok().body(result.getContent());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualiza  um Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Detalhado."),
            @ApiResponse(responseCode = "404", description = "Produto nao encontrado.")
    })
    ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody ProdutoDTO produto) {
        produtoService.atualizar(id, produto);
        return ResponseEntity.ok().build();
    }
}
