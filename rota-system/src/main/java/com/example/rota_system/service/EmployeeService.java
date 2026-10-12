package com.example.rota_system.service;

import com.example.rota_system.dto.Request.CreateEmployeeRequest;
import com.example.rota_system.dto.Response.EmployeeResponse;
import com.example.rota_system.entities.Employee;
import com.example.rota_system.entities.JobRole;
import com.example.rota_system.exception.ResourceNotFoundException;
import com.example.rota_system.mapper.EmployeeMapper;
import com.example.rota_system.repository.EmployeeRepository;
import com.example.rota_system.repository.JobRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private final JobRoleRepository jobRoleRepository;
    private  final EmployeeMapper employeeMapper;

    public EmployeeResponse createEmployee(CreateEmployeeRequest request)
    {
      JobRole jobRole =  jobRoleRepository.findById(request.jobRoleId()).orElseThrow(()-> new ResourceNotFoundException("Job role not found with id"+request.jobRoleId()));
      Employee employee =  employeeMapper.toEntity(request,jobRole);
     Employee savedEmployee =  employeeRepository.save(employee);
     return employeeMapper.toResponse(employee);
    }

    public List<EmployeeResponse> getAllEmployees()
    {
        return employeeRepository.findAllWithJobRole()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
}
