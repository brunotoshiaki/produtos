package io.github.bruno.toshiaki.produtos.output.database;

import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import io.github.bruno.toshiaki.produtos.output.database.model.ProdutoFavorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoFavoritoRepository extends JpaRepository<ProdutoFavorito, Long> {

    Optional<ProdutoFavorito> findByCliente(Cliente cliente);

}
