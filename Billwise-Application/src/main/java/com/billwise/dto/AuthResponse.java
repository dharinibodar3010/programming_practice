package com.billwise.dto;

public class AuthResponse {
    private boolean success;
    private String username;
    private String message;

    public AuthResponse(boolean success, String username, String message) {
        this.success = success;
        this.username = username;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getUsername() { return username; }
    public String getMessage() { return message; }
}
