package com.samuel.contratos.components;

import com.samuel.contratos.service.UserAdmService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InicializacaoAdm implements CommandLineRunner {

    private final UserAdmService userAdmService;

    public void run(String... args) {
        userAdmService.criarUsuarioAdmin("usuarioTeste", "patricia");
        userAdmService.criarUsuarioAdmin("Samuel", "samuel123");
    }
}
