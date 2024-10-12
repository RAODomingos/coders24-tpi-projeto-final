package dev.dluks.brasileirao.dtos;

import java.time.Instant;

public class CustomErrorResponse {
    private Instant timestamp;
    private String error;
    private String path;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(Instant timestamp, String error, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
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
}
