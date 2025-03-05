package com.samuel.contratos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contratos", schema = "public")
@Data
@NoArgsConstructor //adiciona um construtor vazio
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate data;

    @Column(name = "numero_do_contrato")
    private String numeroDoContrato;

    public Contrato(String nome,
                    LocalDate data,
                    String numeroDoContrato) {
        this.nome = nome;
        this.data = data;
        this.numeroDoContrato = numeroDoContrato;
    }
}
