package com.walker.dscommerce.controller.handlers;

import com.walker.dscommerce.dto.CustomErrorDTO;
import com.walker.dscommerce.exception.DataBaseIntegrityViolationException;
import com.walker.dscommerce.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomErrorDTO customErrorDTO = new CustomErrorDTO(
                Instant.now(),
                httpStatus.value(),
                resourceNotFoundException.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(customErrorDTO);
    }

    @ExceptionHandler(DataBaseIntegrityViolationException.class)
    public ResponseEntity<CustomErrorDTO> DataBaseIntegrityViolation(DataBaseIntegrityViolationException dataBaseIntegrityViolationException, HttpServletRequest httpServletRequest){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        CustomErrorDTO customErrorDTO = new CustomErrorDTO(
                Instant.now(),
                httpStatus.value(),
                dataBaseIntegrityViolationException.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(customErrorDTO);
    }
}
