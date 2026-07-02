package com.alexandre.transferenciaagendamento.service;

import com.alexandre.transferenciaagendamento.classes.IntervaloDiasTransferencia;
import com.alexandre.transferenciaagendamento.home.TransferenciaAgendadaHome;
import com.alexandre.transferenciaagendamento.model.bean.TransferenciaAgendadaBean;
import com.alexandre.transferenciaagendamento.model.dto.TaxaTransferenciaDTO;
import com.alexandre.transferenciaagendamento.controller.form.TransferenciaForm;
import com.alexandre.transferenciaagendamento.model.entity.TransferenciaAgendada;
import com.alexandre.transferenciaagendamento.service.interfaces.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private TransferenciaAgendadaHome transferenciaAgendadaHome;


    @Override
    public List<TransferenciaAgendadaBean> listarTransferencias() {
        return transferenciaAgendadaHome.findAllBeans();
    }

    @Override
    public void agendaTransferencia(TransferenciaForm form) {
        TaxaTransferenciaDTO dto = resolveTaxaTransferencia(form);

        TransferenciaAgendada entity = new TransferenciaAgendada(
                form.getContaOrigem(),
                form.getContaDestino(),
                form.getValorTransferencia(),
                dto.getTaxa(),
                dto.getValorTotal(),
                form.getDataAgendamento(),
                form.getDataTransferencia()
        );

        transferenciaAgendadaHome.save(entity);
    }

    public TaxaTransferenciaDTO resolveTaxaTransferencia(TransferenciaForm form){
        BigDecimal valorTransferencia = form.getValorTransferencia();
        Long diasIntervalo = ChronoUnit.DAYS.between(form.getDataAgendamento(),form.getDataTransferencia());

        BigDecimal taxa = calculaTaxa(diasIntervalo, valorTransferencia);
        BigDecimal valorTotal = form.getValorTransferencia().add(taxa);

        return new TaxaTransferenciaDTO(taxa, valorTotal);
    }

    private BigDecimal calculaTaxa(Long numeroDias, BigDecimal valor){
        BigDecimal taxa = BigDecimal.ZERO;

        Optional<IntervaloDiasTransferencia> possivelIntervalo = IntervaloDiasTransferencia.intervalos()
                .stream()
                .filter(k -> k.dentroDoIntervalo(numeroDias))
                .findFirst();

        if(possivelIntervalo.isPresent()){
            IntervaloDiasTransferencia intervalo = possivelIntervalo.get();
            if(intervalo.getTaxaFixa() != null)
                taxa = taxa.add(BigDecimal.valueOf(intervalo.getTaxaFixa()));
            if(intervalo.getTaxaPercentual() != null)
                taxa = taxa.add(
                        valor.multiply(
                                BigDecimal.valueOf(intervalo.getTaxaPercentual())
                                        .divide(BigDecimal.valueOf(100))
                        )
                );
            taxa.setScale(2, RoundingMode.HALF_UP);
        }

        return taxa;
    }

}
