package com.samuel.contratos.controller;

import com.samuel.contratos.controller.dtos.request.ClienteRequestDTO;
import com.samuel.contratos.controller.mappers.ClienteMapper;
import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public String getCustomerForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formulario-cliente";
    }

    @PostMapping
    public String save(@ModelAttribute("cliente") @Valid ClienteRequestDTO request,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formulario-cliente";

        Cliente cliente = mapper.toEntity(request);
        service.save(cliente);

        return "redirect:/inicio";
    }

    @GetMapping("/editar/{id}")
    public String partialUpdate(@PathVariable("id") UUID id,
                                @ModelAttribute("clienteRequest") @Valid ClienteRequestDTO request,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "Atualizar-cliente";

        service.partialUpdate(id, request);
        return "/Atualizar-cliente";
    }

    @GetMapping("/clientsDisplay")
    public String clientsDisplay(@RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "nome", required = false) String name,
                                 Model model) {
        Page<Cliente> clients = service.clientsDisplay(name, page, pageSize);

        Integer max = clients.getTotalPages();
        List<Integer> sizes = List.of(5, 10, 15);

        model.addAttribute("atualPage", page);
        model.addAttribute("clientes", clients.getContent());
        model.addAttribute("termoPesquisa", name);
        model.addAttribute("totalPages", max);
        model.addAttribute("sizes", sizes);
        model.addAttribute("pageSize", pageSize);

        return "Controle-de-clientes";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") UUID id, Model model) {

       Cliente cliente = service.informacoesCliente(id);
       model.addAttribute("cliente", cliente);
       return "informacoes-cliente";
    }
}
