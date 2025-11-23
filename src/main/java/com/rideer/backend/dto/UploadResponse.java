package com.rideer.backend.dto;

public class UploadResponse {

    private boolean success;
    private String key;
    private String url;
    private String message;

    public UploadResponse() {
    }

    public UploadResponse(boolean success, String key, String url, String message) {
        this.success = success;
        this.key = key;
        this.url = url;  // ONLY URL here (no message appended)
        this.message = message;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
