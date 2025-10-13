package com.samuel.contratos.controller.common;

import com.samuel.contratos.model.Cliente;
import com.samuel.contratos.service.ClienteService;
import com.samuel.contratos.service.ContratoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;

@ControllerAdvice
public class GlobalModelAttributesHandler {

    private final ClienteService clienteService;
    private final ContratoService contratoService;


    public GlobalModelAttributesHandler(ClienteService clienteService,
                                        ContratoService contratoService) {
        this.clienteService = clienteService;
        this.contratoService = contratoService;
    }

    @ModelAttribute("listaClientes")
    public List<Cliente> findAllClientes() {
        return clienteService.listarClientes();
    }

    @ModelAttribute("user")
    public User getUser(@AuthenticationPrincipal User user) {
        return user;
    }

}
