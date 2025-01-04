package io.github.bruno.toshiaki.produtos.core.model;



public record ProdutoResponse(
        Long id,
        Double price,
        String image,
        String brand,
        String title,
        Double reviewScore
) {
}
