package com.samuel.contratos.repository;

import com.samuel.contratos.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Page<Cliente> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Cliente c")
    Long countTotalClientes();
}
