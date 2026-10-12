package com.example.rota_system.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEmployeeRequest(
        @NotBlank(message = "Employee name is required")
        String name,
        @NotNull(message = "jobroleId cannot be null")
        Long jobRoleId

) {
}
