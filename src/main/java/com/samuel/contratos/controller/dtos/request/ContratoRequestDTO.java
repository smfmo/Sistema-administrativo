package com.samuel.contratos.controller.dtos.request;

import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Enum.TiposDeContrato;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContratoRequestDTO(
        @NotNull(message = "Agência não pode ser nulo")
        @Size(max = 4)
        String agencia,
        @NotNull(message = "Sr não pode ser nulo")
        String sr,
        String valorBruto,
        String valorLiquido,
        String prestacao,
        String parcelas,
        String prestamista,
        String iof,
        String jurosAcerto,
        LocalDate data,
        Cliente cliente,
        TiposDeContrato tipoContrato,
        String numeroDoContrato
) {}
