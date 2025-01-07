package io.github.bruno.toshiaki.produtos.output.database;

import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(" Select p from Produto p  join p.produtoFavorito pv  join pv.cliente c where p.id =:id and c.id=:idCliente")
    Optional<Produto> findProdutoByIdAndIdCliente(Long id, Long idCliente);
}
