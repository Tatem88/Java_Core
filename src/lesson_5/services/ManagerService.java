package lesson_5.services;

import lesson_5.model.*;
import lesson_5.repository.AssigmentRepository;
import lesson_5.services.exeption.CheckingAccessRights;
import lesson_5.view.View;

import java.util.List;


public class ManagerService {

    private final Employee employee;
    private final Department department;

    private ManagerService(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }

    public static ManagerService factoryManagerService(Employee employee) {
        try {
            if (employee.getSkill() == Skill.MANAGER || employee.getSkill() == Skill.DIRECTOR) {
                return new ManagerService(employee, employee.getDepartment());
            } else throw new CheckingAccessRights();
        } catch (CheckingAccessRights e) {
            View.printConsole(e.getMessage());
        }
        return null;
    }

    public static void assigmentTaskManual() {
        Employee manager = new Employee(Skill.NO_SKILL);
        DepartmentHRService departmentHRService = new DepartmentHRService();
        List<Employee> employeesByDepartment = departmentHRService.getEmployeesByDepartment();
        if (employeesByDepartment == null) {
            View.printConsole("There are no department employees");
            return;
        }
        for (Employee emp : employeesByDepartment) {
            if (emp.getSkill() == Skill.MANAGER)
                manager = emp;
        }
        if (manager.getSkill() == Skill.NO_SKILL) {
            View.printConsole("The department does not have a manager");
            return;
        }
        ManagerService managerService = factoryManagerService(manager);
        assert managerService != null;
        managerService.manualAssignmentTask();

    }

    public Employee getEmployee() {
        return employee;
    }

    public void raisingSalaries(double percentageIncrease) {
        department.getDepartmentEmployee().stream()
                .filter(x -> x.getSkill() != Skill.MANAGER)
                .forEach(x -> x.setSalary(x.getSalary() * (1 + percentageIncrease)));
    }

    public static void informingManager(String message) {
        View.printConsole(message);
    }

    public void manualAssignmentTask() {
        ScannerService scannerService = new ScannerService();
        List<Task> freeTasksDepartment = TaskPlanner.getFreeTask().stream()
                .filter(x -> x.getSkill() == department.getSkill())
                .toList();
        View.printConsoleList(freeTasksDepartment);
        int taskID = scannerService.intScanner("Input task ID");
        List<Task> tasks = freeTasksDepartment.stream()
                .filter(x -> x.getId() == taskID)
                .toList();
        if (tasks.isEmpty()) {
            View.printConsole("Invalid value");
            return;
        }
        Task task = tasks.get(0);
        View.printConsoleList(department.getDepartmentEmployee());
        int taskIDEmployee = scannerService.intScanner("employee ID");
        List<Employee> employees = department.getDepartmentEmployee().stream()
                .filter(x -> x.getId() == taskIDEmployee)
                .toList();
        if (employees.isEmpty()) {
            View.printConsole("Invalid value");
            return;
        }
        Employee employee = employees.get(0);
        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        View.informingEmployee(employee, task.getPriority());
        TaskPlanner.removeFreeTask(task);
    }


}