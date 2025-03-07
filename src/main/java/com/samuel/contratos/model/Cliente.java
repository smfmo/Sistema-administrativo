package com.samuel.contratos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes", schema = "public")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_cliente", nullable = false)
    private String nome;

    @Column(name = "telefone", unique = true, length = 9, nullable = false)
    private String telefone;

    @Column(name = "data_de_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(unique = true, name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "cliente")
    @Transient
    private List<Contrato> contratos;

    //metodos getters e setters gerados pelo Lombok (@Data)
}
