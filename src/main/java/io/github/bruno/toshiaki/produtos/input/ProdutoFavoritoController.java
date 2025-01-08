package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoDTO;
import io.github.bruno.toshiaki.produtos.core.model.ProdutoFavoritoResponse;
import io.github.bruno.toshiaki.produtos.core.service.ProdutoFavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Favoritar", description = "Cadastra um Produto como favorito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente nao encontrado."),
            @ApiResponse(responseCode = "404", description = "Produto nao encontrado."),
            @ApiResponse(responseCode = "422", description = "Produto ja favoritado."),
    })
    public ResponseEntity<Void> cadastrar(@RequestBody ProdutoFavoritoDTO produto) {
        produtoFavoritoService.adicionarCarrinho(produto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remover", description = "Remove um Produto como favorito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso."),
            @ApiResponse(responseCode = "422", description = "Não é possivel remover do favorito."),
    })
    @DeleteMapping("/{id}/{idProduto}")
    public ResponseEntity<Void> remover(@PathVariable Long id, @PathVariable Long idProduto) {
        produtoFavoritoService.removeCarrinho(id,idProduto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar", description = "Listar todos os produtos de um cliente")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<ProdutoFavoritoResponse> listar(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                          @PathVariable("id") Long id) {

        var result = produtoFavoritoService.listagem(page, size, id);

        return ResponseEntity.ok().body(result);
    }

}
