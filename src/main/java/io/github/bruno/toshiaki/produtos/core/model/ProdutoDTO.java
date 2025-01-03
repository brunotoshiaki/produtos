package io.github.bruno.toshiaki.produtos.core.model;

public record ProdutoDTO(
        Double price,
        String image,
        String brand,
        String title,
        Double reviewScore
) {
}
