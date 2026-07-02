package com.alexandre.transferenciaagendamento.home.repository;

import com.alexandre.transferenciaagendamento.model.entity.TransferenciaAgendada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaAgendadaRepository extends JpaRepository<TransferenciaAgendada, Long> {}
