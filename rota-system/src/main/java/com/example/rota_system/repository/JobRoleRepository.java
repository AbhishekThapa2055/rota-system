package com.example.rota_system.repository;

import com.example.rota_system.entities.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRoleRepository extends JpaRepository<JobRole,Long> {
boolean existsByNameIgnoreCase(String name);
boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);

}

