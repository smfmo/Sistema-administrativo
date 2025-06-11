package com.samuel.contratos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes",
        schema = "public")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_cliente",
            nullable = false)
    private String nome;

    @Column(name = "telefone",
            unique = true,
            length = 60,
            nullable = false)
    private String telefone;

    @Column(name = "data_nascimento",
            nullable = false)
    private LocalDate dataNascimento;

    @Column(unique = true,
            name = "cpf",
            nullable = false)
    private String cpf;

    @Column(unique = true,
            nullable = false,
            name = "matricula")
    private int matricula;

    @Column(unique = true,
            nullable = false,
            name = "n_identidade")
    private String numeroIdentidade;

    @Column(nullable = false,
            name = "orgao_emissor")
    private String orgaoEmissor;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "cliente")
    @Transient
    private List<Contrato> contratos;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    //metodos getters e setters gerados pelo Lombok (@Data)
}
