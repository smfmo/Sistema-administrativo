package com.samuel.contratos.controller.dtos;

import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Contrato;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ContratoDTO {
    private LocalDate data;
    private String numeroDoContrato;
    private Cliente cliente;

}
