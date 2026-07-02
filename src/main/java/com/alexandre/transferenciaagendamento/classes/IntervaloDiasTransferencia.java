package com.alexandre.transferenciaagendamento.classes;

import lombok.Getter;

import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public enum IntervaloDiasTransferencia {
    ZERO(0l, 0l, 2.5, 3.0),
    UM_A_DEZ(1l, 10l, null, 12.0),
    ONZE_A_VINTE(11l, 20l, 8.2, null),
    VINTE_UM_A_TRINTA(21l, 30l, 6.9, null),
    TRINTA_UM_A_QUARENTA(31l, 40l, 4.7, null),
    QUARENTA_UM_A_CINQUENTA(41l, 50l, 1.7, null);

    private Long minimo;
    private Long maximo;
    @Getter
    private Double taxaPercentual;
    @Getter
    private Double taxaFixa;

    IntervaloDiasTransferencia(Long minimo, Long maximo, Double taxaPercentual, Double taxaFixa) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.taxaPercentual = taxaPercentual;
        this.taxaFixa = taxaFixa;
    }

    private static final Collection<IntervaloDiasTransferencia> todosValores = unmodifiableList(asList(IntervaloDiasTransferencia.values()));

    public static Collection<IntervaloDiasTransferencia> intervalos() {
        return todosValores;
    }

    public boolean dentroDoIntervalo(long numeroDias) {
        return entreIncluso(numeroDias, this.minimo, this.maximo);
    }

    private <T extends Comparable> boolean entreIncluso(T v, T a, T b) {
        return v.compareTo(a) == 0 || v.compareTo(b) == 0 || (v.compareTo(a) > 0 && v.compareTo(b) < 0);
    }
}
