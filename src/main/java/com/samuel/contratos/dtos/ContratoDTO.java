package com.samuel.contratos.dtos;

import com.samuel.contratos.model.Contrato;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ContratoDTO {
    private String nome;
    private LocalDate data;
    private String numeroDoContrato;

    public Contrato transformaParaObjeto(){
        return new Contrato(nome, data, numeroDoContrato);
    }
}
