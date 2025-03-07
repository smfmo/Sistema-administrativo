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
