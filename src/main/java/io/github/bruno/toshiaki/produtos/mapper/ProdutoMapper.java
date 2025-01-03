package io.github.bruno.toshiaki.produtos.mapper;

import io.github.bruno.toshiaki.produtos.core.model.ProdutoDTO;
import io.github.bruno.toshiaki.produtos.output.database.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProdutoMapper {


    @Mapping(target = "produtoFavorito", ignore = true)
    @Mapping(target = "id", ignore = true)
    Produto fromDTO (ProdutoDTO dto);
}
