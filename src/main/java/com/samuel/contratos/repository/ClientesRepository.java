package com.samuel.contratos.repository;

import com.samuel.contratos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClientesRepository extends JpaRepository<Cliente, UUID> {
}
