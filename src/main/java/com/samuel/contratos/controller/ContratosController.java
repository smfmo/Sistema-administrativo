package com.samuel.contratos.controller;

import com.samuel.contratos.dtos.ContratoDTO;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.model.TiposDeContrato;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/contratos")
public class ContratosController {

    private final ContratoService contratosService;
    private final ClienteService clienteService;

    @Autowired
    public ContratosController(ContratoService contratosService,
                               ClienteService clienteService) {
        this.clienteService = clienteService;
        this.contratosService = contratosService;
    }

    @GetMapping("/form/addContrato")
    public String mostrarFormularioContrato(Model model) {
        model.addAttribute("contrato", new Contrato());
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("tiposDeContrato", TiposDeContrato.values());
        return "formulario-contrato";
    }
    @PostMapping
    public String salvarContrato(@ModelAttribute Contrato contrato) {
        contratosService.salvarContrato(contrato);
        return "redirect:/inicio";
    }
    @GetMapping("/cliente/{id}")
    public String listarContratosPorCliente(@PathVariable UUID id, Model model) {
        List<Contrato> contratosDoCliente = contratosService.listarContratosPorCliente(id);

        model.addAttribute("contratos", contratosDoCliente);
        return "controle-contratos";
    }
}
