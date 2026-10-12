package com.example.rota_system.service;

import com.example.rota_system.dto.Request.JobRoleRequest;
import com.example.rota_system.dto.Request.UpdateJobRoleRequest;
import com.example.rota_system.dto.Response.JobRoleResponse;
import com.example.rota_system.entities.JobRole;
import com.example.rota_system.exception.DuplicateResourceException;
import com.example.rota_system.exception.ResourceNotFoundException;
import com.example.rota_system.mapper.JobRoleMapper;
import com.example.rota_system.repository.JobRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobRoleService {
    private  final JobRoleRepository jobRoleRepository;
    private final JobRoleMapper jobRoleMapper;

    //create jobrole
    public JobRoleResponse createJobRole(JobRoleRequest request)
    {

        if(jobRoleRepository.existsByNameIgnoreCase(request.name()))
        {
            throw new DuplicateResourceException("Job already exists:"+ request.name());
        }

        JobRole jobRole =  jobRoleMapper.toEntity(request);
       JobRole savedJobRole =  jobRoleRepository.save(jobRole);
       return jobRoleMapper.toJobRoleResponse(savedJobRole);
    }

    // get all job roles
public List<JobRoleResponse> getAllJobRoles()
{
    return jobRoleRepository.findAll()
            .stream().map(jobRoleMapper::toJobRoleResponse)
            .toList();
}

//get job role by id

public JobRoleResponse getJobRoleById(Long id)
{
    JobRole jobRole =   jobRoleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job role not found with id:"+id));
   return  jobRoleMapper.toJobRoleResponse(jobRole);
}

//update job role

    public JobRoleResponse updateJobRole(UpdateJobRoleRequest request ,Long id)
    {
        JobRole jobRole =  jobRoleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job Role not found with id:"+id));


        if(jobRoleRepository.existsByNameIgnoreCaseAndIdNot(request.name(), id))
        {
            throw new DuplicateResourceException("Job Role already exists:" + request.name());
        }

        jobRoleMapper.UpdateEntity(request,jobRole);

        JobRole updatedJobRole = jobRoleRepository.save(jobRole);
        return jobRoleMapper.toJobRoleResponse(updatedJobRole);
    }

    //delete the job role

    public void deleteJobRole(Long id)
    {
       JobRole jobRole =  jobRoleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Job role not found with id:"+id));

       jobRoleRepository.delete(jobRole);
    }

}
