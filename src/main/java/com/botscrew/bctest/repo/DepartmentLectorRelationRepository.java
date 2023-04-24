package com.botscrew.bctest.repo;

import com.botscrew.bctest.entity.DepartmentLectorRelation;
import com.botscrew.bctest.entity.embedded_id.DepartmentLectorRelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentLectorRelationRepository extends JpaRepository<DepartmentLectorRelation, DepartmentLectorRelationId> {
    List<DepartmentLectorRelation> findIdDepartmentIdByIdLectorId(Long lectorId);

    List<DepartmentLectorRelation> findIdLectorIdByIdDepartmentId(Long departmentId);
    DepartmentLectorRelation findIsHeadByIdLectorId(Long lectorId);
}
