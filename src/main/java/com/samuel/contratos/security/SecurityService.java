package com.samuel.contratos.security;

import com.samuel.contratos.model.Employee;
import com.samuel.contratos.service.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    private final EmployeeService service;

    public SecurityService(EmployeeService service) {
        this.service = service;
    }

    public Employee getEmployeeAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        return service.findByUsername(username);
    }
}
