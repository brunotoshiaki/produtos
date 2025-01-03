package io.github.bruno.toshiaki.produtos.input.exeption;

import io.github.bruno.toshiaki.produtos.core.exeption.ClienteNotFoundExeption;
import io.github.bruno.toshiaki.produtos.core.exeption.EmailAlreadyRegisteredExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ClienteNotFoundExeption.class)
    public ResponseEntity<StandardError> objectNotFound(ClienteNotFoundExeption e) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(EmailAlreadyRegisteredExeption.class)
    public ResponseEntity<StandardError> emailAlreadyRegistered(EmailAlreadyRegisteredExeption e) {
        StandardError err = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }
}
