package com.samuel.contratos.repository;

import com.samuel.contratos.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByUsername(String username);

}
