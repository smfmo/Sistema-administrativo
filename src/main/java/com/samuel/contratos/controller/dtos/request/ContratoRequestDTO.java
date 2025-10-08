package com.samuel.contratos.controller.dtos.request;

import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Enum.TiposDeContrato;

import java.time.LocalDate;

public record ContratoRequestDTO(
        String agencia,
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
