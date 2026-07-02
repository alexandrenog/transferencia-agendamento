package com.alexandre.transferenciaagendamento.model.bean;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransferenciaAgendadaBean extends EntityRootBean {
    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valorTransferencia;
    private BigDecimal taxa;
    private BigDecimal valorTotal;
    private LocalDate dataAgendamento;
    private LocalDate dataTransferencia;

}
