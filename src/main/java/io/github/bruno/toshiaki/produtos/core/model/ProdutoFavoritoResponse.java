package io.github.bruno.toshiaki.produtos.core.model;

import java.util.Set;


public record ProdutoFavoritoResponse(
        ClienteDTO cliente,
        Set<ProdutoResponse> produtoResponse) {
}
