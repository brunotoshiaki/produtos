package io.github.bruno.toshiaki.produtos.core.exeption;

public class ClienteNotFoundExeption extends RuntimeException {
    public ClienteNotFoundExeption() {
        super("Cliente nao encontrado");
    }


}
