package com.samuel.contratos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "endereco",
        schema = "public")
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "uf")
    private String uf;

    public Endereco() {}
}
