package com.example.rota_system.dto.Request;

import jakarta.validation.constraints.NotBlank;

public record UpdateJobRoleRequest(

        @NotBlank(message = "Job role name is required")
        String name,
        String description


) {
}
