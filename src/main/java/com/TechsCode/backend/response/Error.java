package com.techscode.backend.response;

public class Error {

    private final String location;
    private final String message;

    public Error(String location, String message) {
        this.location = location;
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }
}