package com.samuel.contratos.security;

import com.samuel.contratos.model.Employee;
import com.samuel.contratos.service.EmployeeService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeService service;

    public CustomUserDetailsService(EmployeeService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = service.findByUsername(username);

        if (username == null) {
            throw new UsernameNotFoundException("Employee not found");
        }

        return User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .roles(employee.getRole())
                .build();
    }
}
