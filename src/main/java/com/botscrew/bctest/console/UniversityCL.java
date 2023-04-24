package com.botscrew.bctest.console;

import com.botscrew.bctest.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Profile("!test")
@Component
public class UniversityCL implements CommandLineRunner {

    @Autowired
    private UniversityService universityService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");
            String command = scanner.nextLine();

            if (command.startsWith("Who is head of department ")) {
                String departmentName = command.substring(26);
                String response = universityService.getHeadOfDepartment(departmentName);
                System.out.println(response);
            } else if (command.startsWith("Show the average salary for the department ")) {
                String departmentName = command.substring(43);
                String response = universityService.getDepartmentAverageSalary(departmentName);
                System.out.println(response);
            } else if (command.startsWith("Show count of employee for ")) {
                String departmentName = command.substring(27);
                String response = universityService.getDepartmentEmployeeCount(departmentName);
                System.out.println(response);
            } else if (command.startsWith("Show ")) {
                String departmentName = command.substring(5, command.indexOf(" statistics"));
                String response = universityService.getDepartmentStatistics(departmentName);
                System.out.println(response);
            } else if (command.startsWith("Global search by ")) {
                String template = command.substring(17);
                String response = universityService.searchGlobal(template);
                System.out.println(response);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }

}
