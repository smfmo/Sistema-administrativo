package com.samuel.contratos.service;

import com.samuel.contratos.controller.dtos.request.ClienteRequestDTO;
import com.samuel.contratos.controller.mappers.ClienteMapper;
import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.repository.ClienteRepository;
import com.samuel.contratos.security.SecurityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final SecurityService securityService;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper,
                          SecurityService securityService) {
        this.repository = repository;
        this.mapper = mapper;
        this.securityService = securityService;
    }

    @Transactional
    public void save(Cliente cliente) {
        cliente.setEmployee(securityService.getEmployeeAuthenticated());
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

    public Page<Cliente> clientsDisplay(String name, Integer page, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(page, pageSize);
        if (name != null && !name.trim().isEmpty()) {
            return repository.findByNomeContainingIgnoreCase(name, pageRequest);
        }
        else {
            return repository.findAll(pageRequest);
        }
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente informacoesCliente(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("cliente nao encontrado"));
    }

    public Long contagemDeClientes(){
        return repository.countTotalClientes();
    }

}
