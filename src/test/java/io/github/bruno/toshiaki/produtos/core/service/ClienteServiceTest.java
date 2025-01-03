package io.github.bruno.toshiaki.produtos.core.service;

import io.github.bruno.toshiaki.produtos.core.exeption.ClienteNotFoundExeption;
import io.github.bruno.toshiaki.produtos.core.exeption.EmailAlreadyRegisteredExeption;
import io.github.bruno.toshiaki.produtos.core.model.ClienteDTO;
import io.github.bruno.toshiaki.produtos.core.model.ClienteResponse;
import io.github.bruno.toshiaki.produtos.mapper.ClienteMapper;
import io.github.bruno.toshiaki.produtos.mapper.ClienteResponseMapper;
import io.github.bruno.toshiaki.produtos.output.database.ClienteRepository;
import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private ClienteResponseMapper clienteResponseMapper;

    @InjectMocks
    private ClienteService clienteService;


    @Test
    void shouldSaveClienteWhenEmailNaoCadastrado() {
        var clienteDTO = new ClienteDTO("nome", "email.com");
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        when(clienteMapper.fromDTO(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.findByEmail(any())).thenReturn(Optional.empty());
        clienteService.salvar(clienteDTO);
        verify(clienteRepository, times(1)).save(cliente);
        assertDoesNotThrow(() -> clienteService.salvar(clienteDTO));
    }

    @Test
    void shouldThrowExceptionWhenEmailCadastrado() {
        var clienteDTO = new ClienteDTO("nome", "email.com");
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        when(clienteMapper.fromDTO(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));
        assertThrows(EmailAlreadyRegisteredExeption.class, () -> clienteService.salvar(clienteDTO));
    }

    @Test
    void shouldFindCliente() {
        var clienteResponse = new ClienteResponse(1L, "nome", "email.com");
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setNome("nome");
        cliente.setId(1L);
        when(clienteResponseMapper.fromEntity(cliente)).thenReturn(clienteResponse);
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        var result = clienteService.buscarPorId(1L);
        assertEquals(result, clienteResponse);
    }

    @Test
    void shouldThrowExceptionWhenNotFoundCliente() {
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setNome("nome");
        cliente.setId(1L);
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundExeption.class, () -> clienteService.buscarPorId(1L));
    }

    @Test
    void shouldDeleteCliente() {
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setNome("nome");
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        clienteService.deletar(1L);
        verify(clienteRepository, times(1)).delete(cliente);
        assertDoesNotThrow(() -> clienteService.deletar(1L));
    }

    @Test
    void shouldThrowExceptionWhenClienteNotFoundWhenDeleting() {
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setNome("nome");
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundExeption.class, () -> clienteService.buscarPorId(1L));
    }

    @Test
    void shouldUpdateCliente() {
        var clienteDTO = new ClienteDTO("nome", "email.com");
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setId(1L);
        when(clienteMapper.fromDTO(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        clienteService.atualizar(clienteDTO, 1L);
        assertDoesNotThrow(() -> clienteService.atualizar(clienteDTO, 1L));
    }

    @Test
    void shouldThrowEmailAlreadyRegisteredExeptionWhenUpdating() {
        var clienteDTO = new ClienteDTO("nome", "email.com");
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setId(1L);
        when(clienteMapper.fromDTO(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));
        assertThrows(EmailAlreadyRegisteredExeption.class, () -> clienteService.atualizar(clienteDTO, 1L));
    }

    @Test
    void shouldThrowClienteNotFoundExeptionWhenUpdating() {
        var clienteDTO = new ClienteDTO("nome", "email.com");
        var cliente = new Cliente();
        cliente.setEmail("email.com");
        cliente.setId(1L);
        when(clienteMapper.fromDTO(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundExeption.class, () -> clienteService.atualizar(clienteDTO, 1L));
    }

}