package com.kheirboukhatem.workouttracker.domain.dto;

public class AuthDto {
    public record RegisterRequest(String username, String email, String password) {}
    public record LoginRequest(String email, String password) {}
    public record AuthResponse(String token) {}
}