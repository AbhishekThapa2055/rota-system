package com.example.rota_system.repository;

import com.example.rota_system.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("""
                SELECT e FROM Employee e
                JOIN FETCH e.jobRole\s
               \s""")
    List<Employee> findAllWithJobRole();
}
