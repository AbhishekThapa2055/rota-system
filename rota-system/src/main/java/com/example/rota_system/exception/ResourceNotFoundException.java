package com.example.rota_system.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public  ResourceNotFoundException(String message)
    {
        super(message);
    }
}
