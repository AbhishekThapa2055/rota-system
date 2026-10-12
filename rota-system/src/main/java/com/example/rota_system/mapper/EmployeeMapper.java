package com.example.rota_system.mapper;

import com.example.rota_system.dto.Request.CreateEmployeeRequest;
import com.example.rota_system.dto.Response.EmployeeResponse;
import com.example.rota_system.entities.Employee;
import com.example.rota_system.entities.JobRole;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(CreateEmployeeRequest request, JobRole jobRole)
    {
        return Employee.builder()
                .name(request.name())
                .jobRole(jobRole)
                .build();
    }

    public EmployeeResponse toResponse(Employee employee)
    {
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getJobRole().getId(),employee.getJobRole().getName());
    }
}
