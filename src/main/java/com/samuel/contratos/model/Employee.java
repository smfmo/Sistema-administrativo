package com.samuel.contratos.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Data
@Entity
@Table(name = "employee",
        schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username",
            unique = true,
            nullable = false)
    private String username;

    @Column(name = "password",
            nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

}
