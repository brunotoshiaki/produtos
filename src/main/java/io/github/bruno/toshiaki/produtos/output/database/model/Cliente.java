package io.github.bruno.toshiaki.produtos.output.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(length = 250)
    private String nome;
    @Column(length = 50, unique = true)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private Set<ProdutoFavorito> produtoFavorito = new HashSet<>();

}
