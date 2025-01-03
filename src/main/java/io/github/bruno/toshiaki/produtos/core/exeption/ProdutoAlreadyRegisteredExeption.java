package io.github.bruno.toshiaki.produtos.core.exeption;

public class ProdutoAlreadyRegisteredExeption extends RuntimeException {
    public ProdutoAlreadyRegisteredExeption() {
        super("Produto ja cadastrado!");
    }
}
