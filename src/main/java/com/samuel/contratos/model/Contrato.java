package com.samuel.contratos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contratos",
        schema = "public")
@Data
@NoArgsConstructor //adiciona um construtor vazio
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,
            name = "agencia")
    private String agencia;

    @Column(nullable = false,
            name = "sr")
    private String sr;

    @Column(nullable = false,
            name = "valor_bruto")
    private String valorBruto;

    @Column(nullable = false,
            name = "valor_liquido")
    private String valorLiquido;

    @Column(nullable = false,
            name = "prestacao")
    private String prestacao;

    @Column(nullable = false,
            name = "parcelas")
    private String parcelas;

    @Column(nullable = false,
            name = "prestamista")
    private String prestamista;

    @Column(nullable = false,
            name = "iof")
    private String iof;

    @Column(nullable = false,
            name = "juros_de_acerto")
    private String jurosAcerto;

    @Column(name = "data")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_contrato")
    private TiposDeContrato tipoContrato;

    @Column(name = "numero_do_contrato")
    private String numeroDoContrato;

    public Contrato(LocalDate data,
                    String numeroDoContrato,
                    Cliente cliente) {
        this.data = data;
        this.numeroDoContrato = numeroDoContrato;
        this.cliente = cliente;
    }
}
