package lesson_5.services;

import lesson_5.model.*;
import lesson_5.repository.EmployeeRepository;

import java.util.List;


public class SelectionEmployee {


    DepartmentHRService departmentHRService;
    EmployeeService employeeService;

    public SelectionEmployee(DepartmentHRService departmentHRService, EmployeeService employeeService) {
        this.departmentHRService = departmentHRService;
        this.employeeService = employeeService;
    }


    public Employee selectionEmployee(Skill skill, Priority priority) {

        List<Employee> sortEmployees = EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getSkill() == skill)
                .toList();

        for (Employee employee : sortEmployees) {
            if (!employee.isWorking())
                return employee;
        }
        for (Employee employee : sortEmployees) {
            if (priority == Priority.P1) {
                if (checkEmployeeTaskPriorityP1(employee))
                    return employee;
            } else if (checkingTotalTaskCompletionTime(employee))
                return employee;
        }
        ManagerService.informingManager("No employee suitable for task");
        return new Employee(Skill.NO_SKILL);

    }

    public boolean checkingTotalTaskCompletionTime(Employee employee) {

        int maximumTotalTaskCompletionTime = 61;

        int totalTaskCompletionTime = employeeService.getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getStatus() != Status.COMPLETE)
                .mapToInt(x -> x.getTask().getLength())
                .sum();
        return totalTaskCompletionTime < maximumTotalTaskCompletionTime;
    }

    public boolean checkEmployeeTaskPriorityP1(Employee employee) {
        List<Assigment> assigmentFilterPriority = employeeService.getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getPriority() == Priority.P1)
                .toList();
        return assigmentFilterPriority.isEmpty();

    }
}