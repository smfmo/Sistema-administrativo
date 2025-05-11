package com.samuel.contratos.repository;

import com.samuel.contratos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClientesRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT COUNT(c) FROM Cliente c")
    Long countTotalClientes();
}
