package io.github.bruno.toshiaki.produtos.output.database;

import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import io.github.bruno.toshiaki.produtos.output.database.model.ProdutoFavorito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoFavoritoRepository extends JpaRepository<ProdutoFavorito, Long> {
    @Query("Select p from Produto p  join  p.produtoFavorito pv join pv.id.cliente c where c.id =:idClient")
    Page<Produto> findAllByCliente(Long idClient, Pageable pageable);

    @Query("Select p from ProdutoFavorito p where p.id.cliente.id=:idCliente and p.id.produto.id=:idProduto")
    Optional<ProdutoFavorito> findProdutoFavoritoByIdClienteAndIdProduto(Long idCliente, Long idProduto);

    @Query("Select p from ProdutoFavorito p where p.id.cliente.id=:idCliente")
    Optional<ProdutoFavorito> findProdutoFavoritoByCliente(Long idCliente);

}
