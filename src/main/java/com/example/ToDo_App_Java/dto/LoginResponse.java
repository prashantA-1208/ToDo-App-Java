package com.example.todo_app_java.dto;

public class LoginResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public LoginResponse(String token) {
        this.token = token;
    }

}