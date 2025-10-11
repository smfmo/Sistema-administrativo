package com.samuel.contratos.repository;

import com.samuel.contratos.model.UserAdm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserAdmRepository extends JpaRepository<UserAdm, UUID> {
    Optional<UserAdm> findByUsername(String username);

}
