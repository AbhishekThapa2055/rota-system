package com.example.rota_system.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JobRoleRequest(

        @NotBlank(message = "Job role name is required")
        String name,
        @Size(max=500,message = "Description must not exceed 500 characters")
        String description



) {
}
