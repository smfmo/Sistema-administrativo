package com.samuel.contratos.service;

import com.samuel.contratos.controller.dtos.ClienteDto;
import com.samuel.contratos.controller.mappers.ClienteMapper;
import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ClientesRepository;
import com.samuel.contratos.repository.ContratosRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientesRepository repository;
    private final ContratosRepository contratosRepository;
    private final ClienteMapper mapper;

    @Transactional
    public void save(ClienteDto clienteDto) {
        Cliente cliente = mapper.toEntity(clienteDto);
        repository.save(cliente);
    }

    @Transactional
    public void partialUpdate(UUID id, ClienteDto clienteAtualizado) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found!")
        );
        mapper.updatePartial(clienteAtualizado, clienteExistente);
        repository.save(clienteExistente);
    }

    /** metodo de exclusao e atualizaçao
     * public void excluirCliente(Cliente cliente) {
     *         repository.delete(cliente);
     *     }
     *     public void atualizarDadosCliente(Cliente cliente) {
     *         repository.save(cliente);
     *     }
     */

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }
    public List<Contrato> listarContratosPorCliente(UUID clienteId) {
        return contratosRepository.findByClienteId(clienteId);
    }

    //pesquisar o cliente
    public List<Cliente> pesquisarCliente(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Cliente informacoesCliente(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("cliente nao encontrado"));
    }

    public Long contagemDeClientes(){
        return repository.countTotalClientes();
    } //contagem total dos repository no Daschboard

}
