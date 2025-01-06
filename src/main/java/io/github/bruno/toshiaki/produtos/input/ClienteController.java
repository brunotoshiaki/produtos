package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ClienteDTO;
import io.github.bruno.toshiaki.produtos.core.model.ClienteResponse;
import io.github.bruno.toshiaki.produtos.core.service.ClienteService;
import io.github.bruno.toshiaki.produtos.mapper.ClienteResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
@Tag(name = "Clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteResponseMapper clienteResponseMapper;

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastra um Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Email ja Cadastrado."),
    })
    public ResponseEntity<Void> cadastrar(@RequestBody ClienteDTO cliente) {
        clienteService.salvar(cliente);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar", description = "Busca um Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente nao encontrado."),
    })
    public ResponseEntity<ClienteResponse> buscar(@PathVariable("id") Long id) {
        var cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(clienteResponseMapper.fromEntity(cliente));
    }

    @Operation(summary = "Deletar", description = "Deletar um Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente nao encontrado."),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualziar", description = "Atualiza um Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente nao encontrado."),
            @ApiResponse(responseCode = "422", description = "Email ja cadastrado."),
    })
    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id,
                                          @RequestBody ClienteDTO cliente) {

        clienteService.atualizar(cliente, id);
        return ResponseEntity.ok().build();
    }

}
