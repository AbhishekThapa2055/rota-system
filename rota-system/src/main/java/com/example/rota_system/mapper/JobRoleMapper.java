package com.example.rota_system.mapper;

import com.example.rota_system.dto.Request.JobRoleRequest;
import com.example.rota_system.dto.Request.UpdateJobRoleRequest;
import com.example.rota_system.dto.Response.JobRoleResponse;
import com.example.rota_system.entities.JobRole;
import org.springframework.stereotype.Component;

@Component
public class JobRoleMapper {
    public JobRole toEntity(JobRoleRequest request)
{
    return JobRole.builder()
            .name(request.name())
            .description(request.description())
            .build();
}

public JobRoleResponse toJobRoleResponse(JobRole jobRole)
{
    return new JobRoleResponse(jobRole.getId(),jobRole.getName(), jobRole.getDescription());
}

public void UpdateEntity(UpdateJobRoleRequest request, JobRole jobRole)
{
    jobRole.setName(request.name());
    jobRole.setDescription(request.description());
}
}
