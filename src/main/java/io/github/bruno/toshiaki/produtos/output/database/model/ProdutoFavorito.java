package io.github.bruno.toshiaki.produtos.output.database.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class ProdutoFavorito {

    @EmbeddedId
    @JsonIgnore
    private ItemPk id = new ItemPk();

    public ProdutoFavorito() {

    }

    public ProdutoFavorito(Cliente cliente, Produto produto) {
        super();
        id.setCliente(cliente);
        id.setProduto(produto);
    }
}
