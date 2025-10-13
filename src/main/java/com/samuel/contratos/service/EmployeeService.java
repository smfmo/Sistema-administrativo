package com.samuel.contratos.service;

import com.samuel.contratos.model.Employee;
import com.samuel.contratos.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EmployeeRepository repository;
    private final PasswordEncoder encoder;

    public void criarUsuarioAdmin(String username, String password) {
        if (repository.findByUsername(username).isPresent()){
            return;
        }
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(encoder.encode(password));
        employee.setRole("ADMIN"); //define o papel como administrador

        repository.save(employee);
    }

    public Employee findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }
}
