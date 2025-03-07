package com.samuel.contratos.controller;

import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.model.Contrato;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    private final ClienteService clienteService;
    private final ContratoService contratoService;

    @Autowired
    public ClientesController(ClienteService clienteService,
                              ContratoService contratoService) {
        this.clienteService = clienteService;
        this.contratoService = contratoService;
    }

    @GetMapping("/form/addCliente") //mostrar o formulario do cliente
    public String mostrarFormulario(Model model) {
        model.addAttribute("clientes", new Cliente());
        return "formulario-cliente";
    }

    @PostMapping
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return "redirect:/inicio";
    }

/*   @DeleteMapping
//    public String excluirCliente(Cliente cliente) {
//        clienteService.excluirCliente(cliente);
//        return "Cliente excluido com sucesso!";
}*/

    @GetMapping("/controle/listaClientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.listarClientes();

        //para cada cliente, busca os contratos associados
        for (Cliente cliente : clientes) {
            List<Contrato> contratosDoCliente = clienteService
                    .listarContratosPorCliente(cliente.getId());
            cliente.setContratos(contratosDoCliente);
        }

        model.addAttribute("clientes", clientes);

        return "Controle-de-clientes";
    }

/*    @PutMapping
//    public String atualizarDadosCliente(Cliente cliente) {
//        clienteService.atualizarDadosCliente(cliente);
//        return "Cliente atualizado com sucesso!";
 }*/

}
