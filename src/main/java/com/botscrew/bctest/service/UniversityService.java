package com.botscrew.bctest.service;

public interface UniversityService {
    String getHeadOfDepartment(String departmentName);
    String getDepartmentStatistics(String departmentName);

    String getDepartmentAverageSalary(String departmentName);
    String getDepartmentEmployeeCount(String departmentName);
    String searchGlobal(String template);
}
