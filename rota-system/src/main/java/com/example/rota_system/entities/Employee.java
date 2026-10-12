package com.example.rota_system.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
     private String name;
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "job_role_id",nullable = false)
     private JobRole jobRole;
}
