package com.alexandre.transferenciaagendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.alexandre.transferenciaagendamento.model.entity")
@EnableJpaRepositories(basePackages = "com.alexandre.transferenciaagendamento.home")
@SpringBootApplication
public class TransferenciaAgendamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferenciaAgendamentoApplication.class, args);
    }

}
