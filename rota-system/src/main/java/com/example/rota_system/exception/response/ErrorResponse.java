package com.example.rota_system.exception.response;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,int status, String error , String message, String path) {
}
