package io.github.bruno.toshiaki.produtos.core.exeption;

public class ProdutoNotFoundExeption extends RuntimeException{

    public ProdutoNotFoundExeption() { super("Produto nao encontrado"); }
}
