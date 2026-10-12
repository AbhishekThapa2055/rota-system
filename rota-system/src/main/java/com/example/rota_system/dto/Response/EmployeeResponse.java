package com.example.rota_system.dto.Response;

public record EmployeeResponse(
        Long id,
        String name,
        Long jobRoleId,
        String jobRoleName

) {
}
