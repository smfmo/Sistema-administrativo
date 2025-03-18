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

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String numero;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    //métodos getter e setter gerados pelo lombok
}
