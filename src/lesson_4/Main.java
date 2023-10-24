package lesson_4;

import lesson_4.model.*;
import lesson_4.repository.EmployeeRepository;
import lesson_4.services.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department departmentEngineer = new Department(Skill.ENGINEER);
        Department departmentHR = new Department(Skill.HR);
        Employee employeeHR = new Employee("Ivova"
                , "Lana"
                , LocalDate.of(1990, 3, 15)
                , 100
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employeeHR);
        DepartmentHRService departmentHRService = new DepartmentHRService();
        departmentHRService.appointManager(departmentHR, employeeHR);
        departmentHRService.addDepartment(departmentHR);
        Employee employee1 = new Employee("Luhmanova"
                , "Inna"
                , LocalDate.of(1992, 5, 10)
                , 1000
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee1);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee1);
        Employee employee2 = new Employee("Munz"
                , "Natali"
                , LocalDate.of(1988, 10, 10)
                , 2000
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee2);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee2);
        Employee employee3 = new Employee("Gerasimov"
                , "Anrey"
                , LocalDate.of(1995, 11, 12)
                , 600
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employee3);
        departmentHRService.appointManager(departmentEngineer, employee3);
        Employee employee4 = new Employee("Anisimova"
                , "Olga"
                , LocalDate.of(1978, 9, 2)
                , 250
                , Skill.HR);
        EmployeeRepository.addEmployee(employee4);
        departmentHRService.addEmployeeDepartment(departmentHR, employee4);
        Director director = new Director("Ivleva"
                , "Alina"
                , "Igorevna"
                , LocalDate.of(1980, 7, 27)
                , 5000
                , Skill.DIRECTOR);
        EmployeeRepository.addEmployee(director);
        departmentHRService.addDepartment(departmentEngineer);

        Task task1 = new Task("Task_1"
                , 1,
                LocalDate.now()
                , Skill.ENGINEER
                , 10);

        Task task2 = new Task("Task_2"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);
        Task task3 = new Task("Task_3"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 20);
        task3.setPriority(Priority.P1);
        Task task4 = new Task("Task_4"
                , 2,
                LocalDate.now()
                , Skill.TECHNOLOGIST
                , 20);
        task3.setPriority(Priority.P1);
        Task task5 = new Task("Task_5"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);
        Task task6 = new Task("Task_6"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);

        ManagerService managerService = ManagerService.factoryManagerService(employee3, departmentEngineer);
        EmployeeService employeeService = new EmployeeService();
        SelectionEmployee selectionEmployee = new SelectionEmployee(managerService, departmentHRService, employeeService);
        TaskPlanner taskPlanner = new TaskPlanner(selectionEmployee);

        taskPlanner.planTask(task1);
        employeeService.startTaskByEmployee(employee1);
        taskPlanner.planTask(task2);
        employeeService.startTaskByEmployee(employee2);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task4);
        taskPlanner.planTask(task5);
        taskPlanner.planTask(task6);

        employeeService.startTaskByEmployee(employee3);
        employeeService.startTaskByEmployee(employee4);


        FileService fileService = new FileService();
        fileService.fileWriterEmployee(EmployeeRepository.getEmployees());
        System.out.println(fileService.fileReaderEmployee());
    }
}