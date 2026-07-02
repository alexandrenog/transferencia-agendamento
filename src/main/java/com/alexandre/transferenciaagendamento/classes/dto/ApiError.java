package com.alexandre.transferenciaagendamento.classes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    int status;
    String message;
    List<String> details;
    Instant timestamp;

}
