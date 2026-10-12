package com.example.rota_system.exception;

import com.example.rota_system.exception.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRescoureNotFoundException( ResourceNotFoundException exception)
    {
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),"Not Found",exception.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException exception)
    {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.CONFLICT.value(),"Conflict", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request)
    {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error->error.getField() + ":" + error.getDefaultMessage())
                .orElse("Validation failed");
        ErrorResponse errorResponse  = new ErrorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), "Bad Request",message,request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // UnexcpectedException in this type of excpption we never ever show the  proper message to the client like infrastructre or null pointer excpetion somehing like that

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception exception,
            HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
