package com.botscrew.bctest.service.impl;


import com.botscrew.bctest.entity.Department;
import com.botscrew.bctest.entity.DepartmentLectorRelation;
import com.botscrew.bctest.entity.Lector;
import com.botscrew.bctest.repo.DepartmentLectorRelationRepository;
import com.botscrew.bctest.repo.DepartmentRepository;
import com.botscrew.bctest.repo.LectorRepository;
import com.botscrew.bctest.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LectorRepository lectorRepository;

    @Autowired
    private DepartmentLectorRelationRepository departmentLectorRelationRepository;

    @Override
    public String getHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        Long headId = (long) -1;
        if (department != null) {
            List<DepartmentLectorRelation> dlrs = departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department.getId());
            List<Long> lectorsId = dlrs.stream().map(dlr -> dlr.getId().getLectorId()).toList();
            for (Long l_id : lectorsId) {
               if(departmentLectorRelationRepository.findIsHeadByIdLectorId(l_id).isHead()) {
                    headId = l_id;
               }
            }
            return "Head of " + department.getName() + " department is " + lectorRepository.findById(headId).get().getFull_name();
        }
        return "Department not found";
    }

    @Override
    public String getDepartmentStatistics(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department != null) {
            long assistantCount = lectorRepository.countByDegreeAndDepartments("assistant", department);
            long associateProfessorCount = lectorRepository.countByDegreeAndDepartments("associate professor", department);
            long professorCount = lectorRepository.countByDegreeAndDepartments("professor", department);
            return "assistants - " + assistantCount +
                    ". associate professors - " + associateProfessorCount +
                    ". professors - " + professorCount;
        }
        return "Department not found";
    }

    @Override
    public String getDepartmentAverageSalary(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department == null) {
            return "Department not found";
        }
        List<DepartmentLectorRelation> dlrs = departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department.getId());
        List<Long> lectorsId = dlrs.stream().map(dlr -> dlr.getId().getLectorId()).toList();
        List<Lector> lectors = lectorRepository.findAllByIdIn(lectorsId);
        List<Integer> salaries = lectors.stream().map(Lector::getSalary).toList();
        double averageSalary = salaries.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        return "The average salary of " + departmentName + " is $" + averageSalary;
    }

    @Override
    public String getDepartmentEmployeeCount(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department == null) {
            return "Department not found";
        }
        List<DepartmentLectorRelation> dlrs = departmentLectorRelationRepository.findIdLectorIdByIdDepartmentId(department.getId());
        int employeeCount = dlrs.size();
        return "The count of employees for " + departmentName + " is " + employeeCount;
    }

    @Override
    public String searchGlobal(String template) {
        List<Lector> matchingLectors = lectorRepository.findAll();
        matchingLectors = matchingLectors.stream().filter(l -> l.getFull_name().toLowerCase().contains(template.toLowerCase())).toList();

        if (matchingLectors.isEmpty()) {
            return "No matches found for '" + template + "'";
        } else {
            return matchingLectors.stream().map(Lector::getFull_name)
                    .collect(Collectors.joining(", "));
        }
    }
}
