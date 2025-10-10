package com.samuel.contratos.controller.dtos.request;

import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Enum.TiposDeContrato;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratoRequestDTO(
        @NotNull(message = "Agência não pode ser nulo")
        @Size(max = 4)
        String agencia,
        @NotNull(message = "Sr não pode ser nulo")
        String sr,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.NUMBER)
        BigDecimal valorBruto,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.NUMBER)
        BigDecimal valorLiquido,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.NUMBER)
        BigDecimal prestacao,
        String parcelas,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.NUMBER)
        BigDecimal prestamista,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.NUMBER)
        BigDecimal iof,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.NUMBER)
        BigDecimal jurosAcerto,
        @Past(message = "Data da contratação não pode ser uma data futura")
        LocalDate data,
        Cliente cliente,
        TiposDeContrato tipoContrato,
        String numeroDoContrato
) {}
