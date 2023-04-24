package com.botscrew.bctest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String full_name;
    private String degree;
    private int salary;
    @ManyToMany(mappedBy = "lectors")
    private List<Department> departments;

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", degree='" + degree + '\'' +
                ", salary=" + salary +
                '}';
    }
}