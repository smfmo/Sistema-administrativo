package com.samuel.contratos.service;

import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.model.Endereco;
import com.samuel.contratos.repository.ClientesRepository;
import com.samuel.contratos.repository.ContratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClientesRepository clientes;
    private final ContratosRepository contratos;
    private final ClientesRepository clientesRepository;

    @Autowired
    public ClienteService(ClientesRepository clientes,
                          ContratosRepository contratos, ClientesRepository clientesRepository) {
        this.clientes = clientes;
        this.contratos = contratos;
        this.clientesRepository = clientesRepository;
    }

    public void salvarCliente(Cliente cliente, Endereco endereco) {
        cliente.setEndereco(endereco);
        clientes.save(cliente);
    }

    public void excluirCliente(Cliente cliente) {
        clientes.delete(cliente);
    }

    public void atualizarDadosCliente(Cliente cliente) {
        clientes.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clientes.findAll();
    }
    public List<Contrato> listarContratosPorCliente(UUID clienteId) {
        return contratos.findByClienteId(clienteId);
    }

    //pesquisar o cliente
    public List<Cliente> pesquisarCliente(String nome) {
        return clientes.findByNomeContainingIgnoreCase(nome);
    }

    public Cliente informacoesCliente(UUID id) {
        return clientes.findById(id)
                .orElseThrow(()-> new RuntimeException("cliente nao encontrado"));
    }
}
