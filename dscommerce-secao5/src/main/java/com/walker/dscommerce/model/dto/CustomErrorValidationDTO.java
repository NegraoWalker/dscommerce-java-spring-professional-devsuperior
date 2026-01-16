package com.walker.dscommerce.model.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomErrorValidationDTO extends CustomErrorGeneralDTO {

    private List<FieldDTO> errors = new ArrayList<>();

    public CustomErrorValidationDTO(List<FieldDTO> errors) {
        this.errors = errors;
    }

    public CustomErrorValidationDTO(Instant timestamp, Integer status, String error, String path, List<FieldDTO> errors) {
        super(timestamp, status, error, path);
        this.errors = errors;
    }

    public CustomErrorValidationDTO(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
        this.errors = new ArrayList<>();
    }

    public List<FieldDTO> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.removeIf(error -> error.getFieldName().equals(fieldName));
        errors.add(new FieldDTO(fieldName, message));
    }
}