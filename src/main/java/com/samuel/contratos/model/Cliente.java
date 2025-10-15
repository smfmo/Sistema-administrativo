package com.samuel.contratos.model;

import com.samuel.contratos.model.Enum.EstadoCivil;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cliente",
        schema = "public")
@Data
public class Cliente extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome",
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
            name = "numero_identidade")
    private String numeroIdentidade;

    @Column(nullable = false,
            name = "orgao_emissor")
    private String orgaoEmissor;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "cliente")
    @Transient
    private List<Contrato> contratos;

    @ManyToOne
    @JoinColumn(name = "created_by_employee")
    private Employee employee;

    @Embedded
    private Endereco endereco;

    public Cliente() {}
}
