package com.botscrew.bctest.service.impl;

import com.botscrew.bctest.entity.Department;
import com.botscrew.bctest.entity.DepartmentLectorRelation;
import com.botscrew.bctest.entity.Lector;
import com.botscrew.bctest.entity.embedded_id.DepartmentLectorRelationId;
import com.botscrew.bctest.repo.DepartmentLectorRelationRepository;
import com.botscrew.bctest.repo.DepartmentRepository;
import com.botscrew.bctest.repo.LectorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public final class UniversityServiceImplTest {
    @Mock
    private LectorRepository lectorRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private DepartmentLectorRelationRepository departmentLectorRelationRepository;

    @InjectMocks
    private UniversityServiceImpl universityService;

    @Test
    public void getHeadOfDepartment() {
        Department department = Department.builder().name("Test Department").id(1L).build();
        department.setName("Test Department");

        Lector headLector = Lector.builder().id(1L).full_name("Head Lector").departments(List.of(department)).build();

        Lector lector1 = Lector.builder().id(2L).full_name("Lector 1").departments(List.of(department)).build();

        DepartmentLectorRelation dlr1 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(1L).build()).isHead(true).lector(headLector).department(department).build();

        DepartmentLectorRelation dlr2 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(2L).build()).isHead(false).lector(lector1).department(department).build();

        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        when(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department.getId())).thenReturn(List.of(dlr1, dlr2));
        when(departmentLectorRelationRepository.findIsHeadByIdLectorId(headLector.getId())).thenReturn(dlr1);
        when(departmentLectorRelationRepository.findIsHeadByIdLectorId(lector1.getId())).thenReturn(dlr2);
        when(lectorRepository.findById(headLector.getId())).thenReturn(Optional.of(headLector));
        String result = universityService.getHeadOfDepartment("Test Department");
        assertEquals("Head of Test Department department is Head Lector", result);
    }

    @Test
    public void getDepartmentStatistics() {
        Department department = Department.builder().name("Test Department").id(1L).build();
        Lector lector1 = Lector.builder().id(1L).full_name("John Doe").degree("professor").departments(List.of(department)).build();
        Lector lector2 = Lector.builder().id(2L).full_name("Jane Smith").degree("professor").departments(List.of(department)).build();
        Lector lector3 = Lector.builder().id(3L).full_name("Alice Johnson").degree("assistant").departments(List.of(department)).build();
        Lector lector4 = Lector.builder().id(4L).full_name("Bob Brown").degree("associate professor").departments(List.of(department)).build();
        Lector lector5 = Lector.builder().id(5L).full_name("Charlie Wilson").degree("professor").departments(List.of(department)).build();
        DepartmentLectorRelation dlr1 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(1L).build()).isHead(false).lector(lector1).department(department).build();
        DepartmentLectorRelation dlr2 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(2L).build()).isHead(false).lector(lector2).department(department).build();
        DepartmentLectorRelation dlr3 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(3L).build()).isHead(false).lector(lector3).department(department).build();
        DepartmentLectorRelation dlr4 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(4L).build()).isHead(false).lector(lector4).department(department).build();
        DepartmentLectorRelation dlr5 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(5L).build()).isHead(false).lector(lector5).department(department).build();
        List<DepartmentLectorRelation> mockDlrs = new ArrayList<>();
        mockDlrs.add(dlr1);
        mockDlrs.add(dlr2);
        mockDlrs.add(dlr3);
        mockDlrs.add(dlr4);
        mockDlrs.add(dlr5);

        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        when(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department.getId())).thenReturn(mockDlrs.subList(0, 5));
        when(lectorRepository.countByDegreeAndDepartments("assistant", department)).thenReturn(1L);
        when(lectorRepository.countByDegreeAndDepartments("associate professor", department)).thenReturn(1L);
        when(lectorRepository.countByDegreeAndDepartments("professor", department)).thenReturn(3L);

        String result = universityService.getDepartmentStatistics("Test Department");

        assertEquals("assistants - 1. associate professors - 1. professors - 3", result);
    }

    @Test
    public void getDepartmentAverageSalary() {
        Department department = Department.builder().name("Test Department").id(1L).build();
        Lector lector1 = Lector.builder().id(1L).full_name("John Doe").degree("Professor").salary(5000).departments(List.of(department)).build();
        Lector lector2 = Lector.builder().id(2L).full_name("Jane Smith").degree("Professor").salary(6000).departments(List.of(department)).build();
        department.setLectors(List.of(lector1, lector2));
        DepartmentLectorRelation dlr1 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(1L).build()).isHead(false).lector(lector1).department(department).build();
        DepartmentLectorRelation dlr2 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department.getId()).lectorId(2L).build()).isHead(false).lector(lector2).department(department).build();
        List<DepartmentLectorRelation> mockDlrs = new ArrayList<>();
        mockDlrs.add(dlr1);
        mockDlrs.add(dlr2);

        when(departmentRepository.findByName("Test Department")).thenReturn(department);
        when(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department.getId())).thenReturn(mockDlrs.subList(0, 2));
        when(lectorRepository.findAllByIdIn(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department.getId()).stream().map(dlr -> dlr.getId().getLectorId()).toList())).thenReturn(List.of(lector1, lector2));
        String result = universityService.getDepartmentAverageSalary("Test Department");

        assertEquals("The average salary of Test Department is $5500.0", result);
    }

    @Test
    public void getDepartmentEmployeeCountSuccess() {
        Department department1 = Department.builder().name("Department 1").id(1L).build();
        Department department2 = Department.builder().name("Department 2").id(2L).build();
        Department department3 = Department.builder().name("Department 3").id(3L).build();
        DepartmentLectorRelation dlr1 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department1.getId()).lectorId(1L).build()).isHead(false).build();
        DepartmentLectorRelation dlr2 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department2.getId()).lectorId(2L).build()).isHead(false).build();
        DepartmentLectorRelation dlr3 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department3.getId()).lectorId(3L).build()).isHead(false).build();
        List<DepartmentLectorRelation> mockDlrs = new ArrayList<>();
        mockDlrs.add(dlr1);
        mockDlrs.add(dlr2);
        mockDlrs.add(dlr3);

        when(departmentRepository.findByName("Department 1")).thenReturn(department1);
        when(departmentRepository.findByName("Department 2")).thenReturn(department2);
        when(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department1.getId())).thenReturn(mockDlrs.subList(0, 2));
        when(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department2.getId())).thenReturn(mockDlrs.subList(2, 3));

        String departmentName = "Department 1";
        String expectedOutput = "The count of employees for Department 1 is 2";
        String actualOutput = universityService.getDepartmentEmployeeCount(departmentName);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getDepartmentEmployeeCountFail() {
        Department department1 = Department.builder().name("Department 1").id(1L).build();
        Department department2 = Department.builder().name("Department 2").id(2L).build();
        Department department3 = Department.builder().name("Department 3").id(3L).build();
        DepartmentLectorRelation dlr1 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department1.getId()).lectorId(1L).build()).isHead(false).build();
        DepartmentLectorRelation dlr2 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department2.getId()).lectorId(2L).build()).isHead(false).build();
        DepartmentLectorRelation dlr3 = DepartmentLectorRelation.builder().id(DepartmentLectorRelationId.builder().departmentId(department3.getId()).lectorId(3L).build()).isHead(false).build();
        List<DepartmentLectorRelation> mockDlrs = new ArrayList<>();
        mockDlrs.add(dlr1);
        mockDlrs.add(dlr2);
        mockDlrs.add(dlr3);

        when(departmentRepository.findByName("Department 1")).thenReturn(department1);
        when(departmentRepository.findByName("Department 2")).thenReturn(department2);
        when(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department1.getId())).thenReturn(mockDlrs.subList(0, 2));
        when(departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department2.getId())).thenReturn(mockDlrs.subList(2, 3));

        String departmentName = "Department XYZ";
        String expectedOutput = "Department not found";
        String actualOutput = universityService.getDepartmentEmployeeCount(departmentName);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void searchGlobalSuccess() {
        Lector lector1 = Lector.builder().full_name("John Doe").build();
        Lector lector2 = Lector.builder().full_name("Jane Smith").build();
        Lector lector3 = Lector.builder().full_name("Michael Johnson").build();
        List<Lector> mockLectors = new ArrayList<>();
        mockLectors.add(lector1);
        mockLectors.add(lector2);
        mockLectors.add(lector3);

        when(lectorRepository.findAll()).thenReturn(mockLectors);

        String template = "oe";
        String expectedOutput = "John Doe";
        String actualOutput = universityService.searchGlobal(template);
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void searchGlobalFail() {
        Lector lector1 = Lector.builder().full_name("John Doe").build();
        Lector lector2 = Lector.builder().full_name("Jane Smith").build();
        Lector lector3 = Lector.builder().full_name("Michael Johnson").build();
        List<Lector> mockLectors = new ArrayList<>();
        mockLectors.add(lector1);
        mockLectors.add(lector2);
        mockLectors.add(lector3);

        when(lectorRepository.findAll()).thenReturn(mockLectors);

        String template = "xyz";
        String expectedOutput = "No matches found for 'xyz'";
        String actualOutput = universityService.searchGlobal(template);
        assertEquals(expectedOutput, actualOutput);
    }
}
