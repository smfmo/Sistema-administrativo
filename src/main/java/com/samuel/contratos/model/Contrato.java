package com.samuel.contratos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samuel.contratos.model.Enum.TiposDeContrato;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contrato",
        schema = "public")
@Data
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
    private BigDecimal valorBruto;

    @Column(nullable = false,
            name = "valor_liquido")
    private BigDecimal valorLiquido;

    @Column(nullable = false,
            name = "prestacao")
    private BigDecimal prestacao;

    @Column(nullable = false,
            name = "parcelas")
    private String parcelas;

    @Column(nullable = false,
            name = "prestamista")
    private BigDecimal prestamista;

    @Column(nullable = false,
            name = "iof")
    private BigDecimal iof;

    @Column(nullable = false,
            name = "juros_de_acerto")
    private BigDecimal jurosAcerto;

    @Column(name = "data")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate data;

    @ElementCollection
    @CollectionTable(name = "pdfs_contrato",
    joinColumns = @JoinColumn(name = "contrato_id"))
    private List<String> urlPdf;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_contrato")
    private TiposDeContrato tipoContrato;

    @Column(name = "numero_do_contrato")
    private String numeroDoContrato;

    public Contrato() {}
}
