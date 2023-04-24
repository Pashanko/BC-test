package com.botscrew.bctest.repo;

import com.botscrew.bctest.entity.Department;
import com.botscrew.bctest.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    long countByDegreeAndDepartments(String degree, Department department);
    List<Lector> findAll();
    List<Lector> findAllByIdIn(List<Long> lectorId);

}
