package com.alexandre.transferenciaagendamento.model.dto;

import java.math.BigDecimal;

public class TaxaTransferenciaDTO {
    private BigDecimal taxa;
    private BigDecimal valorTotal;

    public TaxaTransferenciaDTO(BigDecimal taxa, BigDecimal valorTotal) {
        this.taxa = taxa;
        this.valorTotal = valorTotal;
    }

    public BigDecimal getTaxa() { return taxa; }
    public BigDecimal getValorTotal() { return valorTotal; }
}
