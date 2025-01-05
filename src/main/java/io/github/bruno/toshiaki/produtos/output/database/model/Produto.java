package io.github.bruno.toshiaki.produtos.output.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private Double price;

    @Column
    private String image;

    @Column
    private String brand;

    @Column
    private String title;

    @Column
    private Double reviewScore;

    @ManyToOne
    @JoinColumn(name = "id_produto_favorito")
    private ProdutoFavorito produtoFavorito;

}
