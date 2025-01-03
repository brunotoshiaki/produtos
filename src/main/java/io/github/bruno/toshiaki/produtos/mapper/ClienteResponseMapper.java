package io.github.bruno.toshiaki.produtos.mapper;

import io.github.bruno.toshiaki.produtos.core.model.ClienteResponse;
import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteResponseMapper {
    ClienteResponse fromEntity(Cliente cliente);
}
