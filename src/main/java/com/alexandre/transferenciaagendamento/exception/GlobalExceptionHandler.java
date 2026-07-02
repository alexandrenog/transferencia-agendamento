package com.alexandre.transferenciaagendamento.exception;

import com.alexandre.transferenciaagendamento.classes.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleValidation(ValidationRuntimeException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                List.of(ex.getMessage()),
                Instant.now()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado",
                List.of(ex.getMessage()),
                Instant.now()
        );
        return ResponseEntity.internalServerError().body(error);
    }
}
