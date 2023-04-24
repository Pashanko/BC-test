package com.botscrew.bctest.entity;

import com.botscrew.bctest.entity.embedded_id.DepartmentLectorRelationId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "department_lector")
public class DepartmentLectorRelation {
    @EmbeddedId
    private DepartmentLectorRelationId id;

    @Column(name = "is_head")
    private boolean isHead;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("departmentId")
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lectorId")
    @JoinColumn(name = "lector_id")
    private Lector lector;


}
