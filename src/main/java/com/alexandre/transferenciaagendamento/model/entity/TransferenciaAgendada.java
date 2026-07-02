package com.alexandre.transferenciaagendamento.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transferencia_agendada")
public class TransferenciaAgendada extends EntityRoot{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conta_origem", nullable = false)
    private String contaOrigem;

    @Column(name = "conta_destino", nullable = false)
    private String contaDestino;

    @Column(name = "valor_transferencia", precision = 15, scale = 2)
    private BigDecimal valorTransferencia;

    @Column(precision = 15, scale = 2)
    private BigDecimal taxa;

    @Column(name = "valor_total", precision = 15, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_agendamento")
    private LocalDate dataAgendamento;

    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;

    public TransferenciaAgendada() {
    }

    public TransferenciaAgendada(String contaOrigem, String contaDestino,
                                 BigDecimal valorTransferencia, BigDecimal taxa,
                                 BigDecimal valorTotal, LocalDate dataAgendamento,
                                 LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorTransferencia = valorTransferencia;
        this.taxa = taxa;
        this.valorTotal = valorTotal;
        this.dataAgendamento = dataAgendamento;
        this.dataTransferencia = dataTransferencia;
    }
}
