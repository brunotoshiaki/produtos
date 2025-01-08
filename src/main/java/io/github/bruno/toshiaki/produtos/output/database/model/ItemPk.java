package io.github.bruno.toshiaki.produtos.output.database.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ItemPk {


    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
