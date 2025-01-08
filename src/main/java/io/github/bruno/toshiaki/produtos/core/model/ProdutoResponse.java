package io.github.bruno.toshiaki.produtos.core.model;



import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProdutoResponse  extends RepresentationModel<ProdutoResponse> {
    private Long id;
    private Double price;
    private String image;
    private String brand;
    private String title;
    private Double reviewScore;

    public ProdutoResponse(Long id, Double price, String image, String brand, String title, Double reviewScore) {
        this.id = id;
        this.price = price;
        this.image = image;
        this.brand = brand;
        this.title = title;
        this.reviewScore = reviewScore;
    }


}

