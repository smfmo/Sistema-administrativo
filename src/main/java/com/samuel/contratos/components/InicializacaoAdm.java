package com.samuel.contratos.components;

import com.samuel.contratos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InicializacaoAdm implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        userService.criarUsuarioAdmin("usuarioTeste", "patricia");
        userService.criarUsuarioAdmin("Samuel", "samuel123");
    }
}
