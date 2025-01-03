package io.github.bruno.toshiaki.produtos.core.exeption;

public class EmailAlreadyRegisteredExeption extends RuntimeException{
    public EmailAlreadyRegisteredExeption() {super("Email ja cadastrado");}
}
