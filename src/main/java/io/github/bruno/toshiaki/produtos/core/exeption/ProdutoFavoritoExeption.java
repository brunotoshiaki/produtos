package io.github.bruno.toshiaki.produtos.core.exeption;

public class ProdutoFavoritoExeption extends RuntimeException {

    public ProdutoFavoritoExeption() {
        super("Nao foi possivel remover o item de favorito");
    }
}
