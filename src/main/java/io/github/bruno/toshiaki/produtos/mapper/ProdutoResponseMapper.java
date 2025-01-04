package io.github.bruno.toshiaki.produtos.mapper;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoResponse;
import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import org.mapstruct.Mapper;

@Mapper
public interface ProdutoResponseMapper {

    ProdutoResponse fromEntity(Produto produto);
}
