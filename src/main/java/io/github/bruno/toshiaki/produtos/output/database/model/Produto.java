package io.github.bruno.toshiaki.produtos.output.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString(exclude = {"cliente"})
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "id_produto")
    private Cliente cliente;

}
