package com.samuel.contratos.controller;

import com.samuel.contratos.controller.dtos.request.ClienteRequestDTO;
import com.samuel.contratos.controller.mappers.ClienteMapper;
import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesController {

    private final ClienteService service;
    private final ClienteMapper mapper;

    @GetMapping
    public String getCustomerForm(@ModelAttribute ClienteRequestDTO request,
                                  Model model) {
        model.addAttribute("clienteRequest", mapper.toEntity(request));
        return "formulario-cliente";
    }

    @PostMapping
    public String save(@ModelAttribute("clienteDto") @Valid ClienteRequestDTO request,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se houver erros no formulário, volta para o mesmo
            return "formulario-cliente";
        }
        service.save(request);
        return "redirect:/inicio";
    }

    @GetMapping("/editar/{id}")
    public String partialUpdate(@PathVariable("id") UUID id,
                                @ModelAttribute("clienteRequest") @Valid ClienteRequestDTO request) {
        service.partialUpdate(id, request);
        return "/Atualizar-cliente";
    }

    @GetMapping("/list")
    public String listarClientes(Model model) {
        List<Cliente> clientes = service.listarClientes();
        model.addAttribute("clientes", clientes);

        return "Controle-de-clientes";
    }

    @GetMapping("/search")
    public String pesquisarCliente(@RequestParam(name = "nome", required = false) String nome,
                                   Model model) {

        List<Cliente> resultado = service.pesquisarCliente(nome);
        model.addAttribute("clientes", resultado);

        return "Controle-de-clientes";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") UUID id, Model model) {

       Cliente cliente = service.informacoesCliente(id);
       model.addAttribute("cliente", cliente);
       return "informacoes-cliente";
    }
}
