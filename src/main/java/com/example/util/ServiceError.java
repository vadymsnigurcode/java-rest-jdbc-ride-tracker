package com.example.util;

public class ServiceError {
    String message;
    int code;

    public ServiceError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceError() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
