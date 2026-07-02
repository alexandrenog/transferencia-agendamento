package com.alexandre.transferenciaagendamento.service.interfaces;

import com.alexandre.transferenciaagendamento.model.bean.TransferenciaAgendadaBean;
import com.alexandre.transferenciaagendamento.model.dto.TaxaTransferenciaDTO;
import com.alexandre.transferenciaagendamento.controller.form.TransferenciaForm;

import java.util.List;

public interface TransferenciaService {
    TaxaTransferenciaDTO resolveTaxaTransferencia(TransferenciaForm req);

    List<TransferenciaAgendadaBean> listarTransferencias();

    void agendaTransferencia(TransferenciaForm req);
}
