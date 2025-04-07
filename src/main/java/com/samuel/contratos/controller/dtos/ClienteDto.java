package com.samuel.contratos.controller.dtos;

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
}
