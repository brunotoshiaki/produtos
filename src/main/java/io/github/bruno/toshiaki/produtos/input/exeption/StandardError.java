package io.github.bruno.toshiaki.produtos.input.exeption;

public record StandardError(
        Integer status,
        String msg,
        Long timeStamp
) {
}
