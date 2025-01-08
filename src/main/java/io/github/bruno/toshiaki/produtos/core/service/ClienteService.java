package io.github.bruno.toshiaki.produtos.core.service;

import io.github.bruno.toshiaki.produtos.core.exeption.ClienteNotFoundExeption;
import io.github.bruno.toshiaki.produtos.core.exeption.EmailAlreadyRegisteredExeption;
import io.github.bruno.toshiaki.produtos.core.model.ClienteDTO;
import io.github.bruno.toshiaki.produtos.mapper.ClienteMapper;
import io.github.bruno.toshiaki.produtos.output.database.ClienteRepository;
import io.github.bruno.toshiaki.produtos.output.database.ProdutoFavoritoRepository;
import io.github.bruno.toshiaki.produtos.output.database.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ProdutoFavoritoRepository produtoFavoritoRepository;


    public void salvar(ClienteDTO clienteDTO) {
        var cliente = clienteMapper.fromDTO(clienteDTO);
        if (emailExists(cliente.getEmail())) {
            throw new EmailAlreadyRegisteredExeption();
        } else {
            clienteRepository.save(cliente);
        }
    }

    private boolean emailExists(String email) {
        return clienteRepository.findByEmail(email).isPresent();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(ClienteNotFoundExeption::new);
    }


    public void deletar(Long id) {
        var cliente = this.buscarPorId(id);
        var produtoFavorito =  produtoFavoritoRepository.findProdutoFavoritoByCliente(cliente.getId());
        produtoFavorito.ifPresent(produtoFavoritoRepository::delete);
        clienteRepository.delete(cliente);
    }

    public void atualizar(ClienteDTO clienteDTO, Long id) {
        var clienteDto = clienteMapper.fromDTO(clienteDTO);

        var clienteEncontrado = this.buscarPorId(id);

        if (!clienteEncontrado.getEmail().trim().equalsIgnoreCase(clienteDto.getEmail().trim())) {
            if (emailExists(clienteDto.getEmail())) {
                throw new EmailAlreadyRegisteredExeption();
            }
            clienteRepository.save(getCliente(id, clienteDto));
        }

    }

    private static Cliente getCliente(Long id, Cliente clienteDto) {
        var newCliente = new Cliente();
        newCliente.setId(id);
        newCliente.setEmail(clienteDto.getEmail());
        newCliente.setNome(clienteDto.getNome());
        return newCliente;
    }


}
