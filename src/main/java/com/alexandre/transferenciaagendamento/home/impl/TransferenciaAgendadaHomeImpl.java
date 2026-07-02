package com.alexandre.transferenciaagendamento.home.impl;

import com.alexandre.transferenciaagendamento.home.TransferenciaAgendadaHome;
import com.alexandre.transferenciaagendamento.home.repository.TransferenciaAgendadaRepository;
import com.alexandre.transferenciaagendamento.model.bean.TransferenciaAgendadaBean;
import com.alexandre.transferenciaagendamento.model.entity.TransferenciaAgendada;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransferenciaAgendadaHomeImpl
        extends EntityRootHomeImpl<TransferenciaAgendada, TransferenciaAgendadaBean, TransferenciaAgendadaRepository>
        implements TransferenciaAgendadaHome {

    @Override
    public TransferenciaAgendadaBean entityToBean(TransferenciaAgendada entity) {
        TransferenciaAgendadaBean bean = entityToSimpleBean(entity, new TransferenciaAgendadaBean());
        bean.setContaOrigem(entity.getContaOrigem());
        bean.setContaDestino(entity.getContaDestino());
        bean.setValorTransferencia(entity.getValorTransferencia());
        bean.setTaxa(entity.getTaxa());
        bean.setValorTotal(entity.getValorTotal());
        bean.setDataAgendamento(entity.getDataAgendamento());
        bean.setDataTransferencia(entity.getDataTransferencia());
        return bean;

    }


}
