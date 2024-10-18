package dev.dluks.brasileirao.dtos.response;

import java.time.Instant;

public class CustomErrorResponse {
    private Instant timestamp;
    private String error;
    private String path;
    private Integer status;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(Instant timestamp, String error, String path, Integer status) {
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
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

    public Integer getStatus() {return status;}

    public void setStatus(Integer status) {this.status = status;}
}
