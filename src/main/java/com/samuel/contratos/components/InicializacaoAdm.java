package com.samuel.contratos.components;

import com.samuel.contratos.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InicializacaoAdm implements CommandLineRunner {

    private final EmployeeService employeeService;

    @Override
    public void run(String... args) {
        employeeService.createEmployee("usuarioTeste", "patricia");
        employeeService.createEmployee("Samuel", "samuel123");
    }
}
