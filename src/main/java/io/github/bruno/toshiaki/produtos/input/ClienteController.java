package io.github.bruno.toshiaki.produtos.input;

import io.github.bruno.toshiaki.produtos.core.model.ClienteDTO;
import io.github.bruno.toshiaki.produtos.core.model.ClienteResponse;
import io.github.bruno.toshiaki.produtos.core.service.ClienteService;
import io.github.bruno.toshiaki.produtos.mapper.ClienteResponseMapper;
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
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteResponseMapper clienteResponseMapper;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody ClienteDTO cliente) {
        clienteService.salvar(cliente);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscar(@PathVariable("id") String id) {
        var cliente = clienteService.buscarPorId(Long.parseLong(id));
        return ResponseEntity.ok(clienteResponseMapper.fromEntity(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        clienteService.deletar(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id,
                                          @RequestBody ClienteDTO cliente) {

        clienteService.atualizar(cliente, Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

}
