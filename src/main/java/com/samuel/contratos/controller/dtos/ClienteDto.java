package com.samuel.contratos.controller.dtos;

import com.samuel.contratos.model.Enum.EstadoCivil;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;

public record ClienteDto(
        @NotBlank(message = "Campo Obrigatório")
                @Size(max = 255, min = 2, message = "Campo fora do tamanho padrão ")
        String nome,
        @NotNull(message = "campo obrigatório")
                @Pattern(regexp = "^(\\(?\\d{2}\\)?[\\s-]?)?(\\d{4,5}[\\s-]?\\d{4})$",
                        message = "número de celular inválido Use (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
        String telefone,
        @NotNull(message = "campo obrigátorio")
        @Past
        LocalDate dataNascimento,
        @CPF
        String cpf,
        @NotNull(message = "Campo obrigatório")
        Integer matricula,
        @NotNull(message = "Campo obrigatório")
        String numeroIdentidade,
        @NotBlank(message = "Campo obrigatório")
                @Size(max = 10, min = 4, message = "campo fora do tamanho padrão")
        String orgaoEmissor,
        EstadoCivil estadoCivil,
        @Email
        String email,
        EnderecoDto endereco) {
}
