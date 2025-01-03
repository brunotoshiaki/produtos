package io.github.bruno.toshiaki.produtos.mapper;

import io.github.bruno.toshiaki.produtos.core.model.ClienteDTO;
import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface ClienteMapper {
    @Mapping(target = "produtoFavorito", ignore = true)
    @Mapping(target = "id", ignore = true)
    Cliente fromDTO(ClienteDTO clienteDTO);

}
