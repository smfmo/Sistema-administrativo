package com.samuel.contratos.controller.dtos;

import com.samuel.contratos.model.Cliente;
import java.time.LocalDate;

public record ClienteDto(
        String nome,
        String telefone,
        LocalDate dataNascimento,
        String cpf,
        Integer matricula,
        String numeroIdentidade,
        String orgaoEmissor,
        String estadoCivil,
        String email,
        EnderecoDto endereco) {

    public Cliente mapearParaCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setDataNascimento(dataNascimento);
        cliente.setCpf(cpf);
        cliente.setMatricula(matricula);
        cliente.setNumeroIdentidade(numeroIdentidade);
        cliente.setOrgaoEmissor(orgaoEmissor);
        cliente.setEstadoCivil(estadoCivil);
        cliente.setEmail(email);
        cliente.setEndereco(endereco.mapearParaEndereco());

        return cliente;
    }
}
