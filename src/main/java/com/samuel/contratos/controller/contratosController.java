package com.samuel.contratos.controller;

import com.samuel.contratos.dtos.ContratoDTO;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/contratos")
@RestController
public class contratosController {

    private final ContratoService contratosService;

    @Autowired
    public contratosController(ContratoService contratosService) {
        this.contratosService = contratosService;
    }

    @PostMapping
    public ResponseEntity<Contrato> salvarContrato(@RequestBody ContratoDTO dto) {
        Contrato contrato1 = contratosService.salvarContrato(dto.transformaParaObjeto());
        return new ResponseEntity<>(contrato1, HttpStatus.CREATED);
    }
}
