package com.samuel.contratos.service;

import com.samuel.contratos.controller.dtos.ClienteDto;
import com.samuel.contratos.controller.mappers.ClienteMapper;
import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.repository.ClientesRepository;
import com.samuel.contratos.repository.ContratosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientesRepository clientes;
    private final ContratosRepository contratos;
    private final ClienteMapper clienteMapper;

    public void salvarCliente(ClienteDto clienteDto) {

        Cliente cliente = clienteMapper.toEntity(clienteDto);
        clientes.save(cliente);
    }

    /** metodo de exclusao e atualizaçao
     * public void excluirCliente(Cliente cliente) {
     *         clientes.delete(cliente);
     *     }
     *     public void atualizarDadosCliente(Cliente cliente) {
     *         clientes.save(cliente);
     *     }
     */

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

    public Long contagemDeClientes(){
        return clientes.countTotalClientes();
    } //contagem total dos clientes no Daschboard

}
