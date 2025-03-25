package com.samuel.contratos.controller;

import com.samuel.contratos.model.Endereco;
import com.samuel.contratos.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class EnderecoController {

    private final EnderecoService service;

    @Autowired
    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public Endereco getEndereco(@PathVariable String cep) {
        return service.buscarEnderecoPeloCep(cep);
    }

}
