package com.walker.dscommerce.dto;

import java.time.Instant;

public class CustomErrorGeneralDTO {
//    Json de resposta, quando ocorre algum erro:
//    {
//       "timestamp": "2025-09-09T11:13:45.929+00:00",
//       "status": 500,
//       "error": "Internal Server Error",
//       "path": "/api/products/26"
//    }


    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    public CustomErrorGeneralDTO() {
    }

    public CustomErrorGeneralDTO(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
