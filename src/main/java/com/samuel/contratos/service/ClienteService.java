package com.samuel.contratos.service;

import com.samuel.contratos.controller.dtos.request.ClienteRequestDTO;
import com.samuel.contratos.controller.mappers.ClienteMapper;
import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public void save(ClienteRequestDTO request) {
        Cliente cliente = mapper.toEntity(request);
        repository.save(cliente);
    }

    @Transactional
    public void partialUpdate(UUID id, ClienteRequestDTO updatedClient) {
        Cliente existingClient = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found!")
        );
        mapper.updatePartial(updatedClient, existingClient);
        repository.save(existingClient);
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public List<Cliente> pesquisarCliente(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Cliente informacoesCliente(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("cliente nao encontrado"));
    }

    public Long contagemDeClientes(){
        return repository.countTotalClientes();
    }

}
