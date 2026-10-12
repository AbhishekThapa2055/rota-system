package com.example.rota_system.controller;

import com.example.rota_system.dto.Request.JobRoleRequest;
import com.example.rota_system.dto.Request.UpdateJobRoleRequest;
import com.example.rota_system.dto.Response.JobRoleResponse;
import com.example.rota_system.service.JobRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-roles")
public class JobRoleController {
    private final JobRoleService jobRoleService;
@PostMapping
    public ResponseEntity<JobRoleResponse> createJobRole(@Valid @RequestBody JobRoleRequest request)
    {
       JobRoleResponse response= jobRoleService.createJobRole(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<JobRoleResponse>> getAllJobRoles()
    {
        List<JobRoleResponse> response = jobRoleService.getAllJobRoles();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobRoleResponse> getJobRoleById(@PathVariable Long id )
    {
        JobRoleResponse response = jobRoleService.getJobRoleById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobRoleResponse> updateJobRole(@PathVariable Long id , @Valid @RequestBody UpdateJobRoleRequest request)
    {

        JobRoleResponse response = jobRoleService.updateJobRole(request,id);
        return ResponseEntity.ok(response);
    }
@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobRole(@PathVariable Long id)
{
    jobRoleService.deleteJobRole(id);
    return  ResponseEntity.ok("deleted succesfully");
}
}
