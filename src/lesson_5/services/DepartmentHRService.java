package lesson_5.services;

import lesson_5.model.Department;
import lesson_5.model.Employee;
import lesson_5.model.Skill;
import lesson_5.repository.DepartmentRepository;
import lesson_5.repository.EmployeeRepository;
import lesson_5.services.exeption.SkillException;
import lesson_5.view.View;

import java.time.LocalDate;
import java.util.List;

public class DepartmentHRService {


    public ScannerService scannerService = new ScannerService();


    public void addEmployeeDepartment(Department department, Employee employee) {
        department.addEmployee(employee);
        employee.setDepartment(department);
    }

    public void appointManager(Department department, Employee manager) {
        try {
            if (manager.getSkill() == Skill.MANAGER || manager.getSkill() == Skill.DIRECTOR) {
                department.setManager(manager);
                manager.setDepartment(department);
            } else throw new SkillException();
        } catch (SkillException e) {
            View.printConsole(e.getMessage());
        }
    }

    public List<Employee> getEmployeesByDepartment() {
        Department department = DepartmentRepository.getDepartmentBySkill(scannerService.skillScanner("Skill department"));
        return department.getDepartmentEmployee();
    }

    public void createEmployee() {

        String lastName = scannerService.stringScanner("Last name");
        String firstName = scannerService.stringScanner("Name");
        LocalDate birthDay = scannerService.dateScanner("Birthday");
        double salary = scannerService.intScanner("salary");
        Skill skill = scannerService.skillScanner("Skill");
        Employee employee = new Employee(lastName, firstName, birthDay, salary, skill);
        EmployeeRepository.addEmployee(employee);
        Skill skillDepartment = scannerService.skillScanner("Skill department");
        Department department = DepartmentRepository.getDepartmentBySkill(skillDepartment);
        if (department.getSkill() == Skill.NO_SKILL)
            department = new Department(skillDepartment);
        addEmployeeDepartment(department, employee);
    }
}