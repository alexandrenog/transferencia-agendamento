package com.alexandre.transferenciaagendamento.configuration;

import com.alexandre.transferenciaagendamento.TransferenciaAgendamentoApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TransferenciaAgendamentoApplication.class);
    }

}
