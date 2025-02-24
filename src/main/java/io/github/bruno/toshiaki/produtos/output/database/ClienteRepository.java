package io.github.bruno.toshiaki.produtos.output.database;

import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

 Optional<Cliente> findByEmail(String email);
}
