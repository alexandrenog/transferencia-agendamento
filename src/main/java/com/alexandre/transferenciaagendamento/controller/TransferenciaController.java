package com.alexandre.transferenciaagendamento.controller;

import com.alexandre.transferenciaagendamento.model.bean.TransferenciaAgendadaBean;
import com.alexandre.transferenciaagendamento.model.dto.TaxaTransferenciaDTO;
import com.alexandre.transferenciaagendamento.controller.form.TransferenciaForm;
import com.alexandre.transferenciaagendamento.service.interfaces.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferenciaController {

    @Autowired
    TransferenciaService transferenciaService;

    @PostMapping("/calcula-taxa")
    public ResponseEntity<TaxaTransferenciaDTO> resolveTaxaTransferencia(@RequestBody TransferenciaForm req) {
        return ResponseEntity.ok(transferenciaService.resolveTaxaTransferencia(req));
    }

    @PostMapping("/agenda-transferencia")
    public ResponseEntity<String> agendaTransferencia(@RequestBody TransferenciaForm req) {
        transferenciaService.agendaTransferencia(req);
        return ResponseEntity.ok("Transferencia Agendada!");
    }

    @GetMapping("listar-transferencias")
    public ResponseEntity<List<TransferenciaAgendadaBean>> listarTransferencias(){
        return ResponseEntity.ok(transferenciaService.listarTransferencias());
    }


}