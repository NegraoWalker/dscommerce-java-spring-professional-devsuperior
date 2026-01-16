package com.walker.dscommerce.controller.handlers;

import com.walker.dscommerce.model.dto.CustomErrorGeneralDTO;
import com.walker.dscommerce.model.dto.CustomErrorValidationDTO;
import com.walker.dscommerce.exception.DataBaseIntegrityViolationException;
import com.walker.dscommerce.exception.ForbiddenException;
import com.walker.dscommerce.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorGeneralDTO> resourceNotFound(ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomErrorGeneralDTO customErrorGeneralDTO = new CustomErrorGeneralDTO(
                Instant.now(),
                httpStatus.value(),
                resourceNotFoundException.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(customErrorGeneralDTO);
    }

    @ExceptionHandler(DataBaseIntegrityViolationException.class)
    public ResponseEntity<CustomErrorGeneralDTO> DataBaseIntegrityViolation(DataBaseIntegrityViolationException dataBaseIntegrityViolationException, HttpServletRequest httpServletRequest){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        CustomErrorGeneralDTO customErrorGeneralDTO = new CustomErrorGeneralDTO(
                Instant.now(),
                httpStatus.value(),
                dataBaseIntegrityViolationException.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(customErrorGeneralDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorValidationDTO> methodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        // Criando o objeto de erro de validação
        CustomErrorValidationDTO customErrorValidationDTO = new CustomErrorValidationDTO(
                Instant.now(),
                httpStatus.value(),
                "Dados informados inválidos",
                httpServletRequest.getRequestURI()
        );

        // Iterando sobre os erros de campo do Spring Boot
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            customErrorValidationDTO.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // Retornando o objeto correto
        return ResponseEntity.status(httpStatus).body(customErrorValidationDTO);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<CustomErrorGeneralDTO> forbiddenException(ForbiddenException e, HttpServletRequest httpServletRequest) { // Corrigido aqui
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        CustomErrorGeneralDTO customErrorGeneralDTO = new CustomErrorGeneralDTO(
                Instant.now(),
                httpStatus.value(),
                e.getMessage(), // Use o objeto correto 'e'
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(customErrorGeneralDTO);
    }

}
