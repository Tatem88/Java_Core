package lesson_4.services;

import lesson_4.model.*;
import lesson_4.repository.AssigmentRepository;
import lesson_4.services.exeption.CheckingAccessRights;
import lesson_4.view.Input;
import lesson_4.view.View;

import java.util.List;

public class ManagerService {

    private final Employee employee;
    private final Department department;

    private ManagerService(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }

    public static ManagerService factoryManagerService(Employee employee, Department department) {
        try {
            if (department.getManager() == employee || employee.getSkill() == Skill.DIRECTOR) {
                return new ManagerService(employee, department);
            } else throw new CheckingAccessRights();
        } catch (CheckingAccessRights e) {
            View.printConsole(e.getMessage());
        }
        return null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void raisingSalaries(double percentageIncrease) {
        department.getDepartmentEmployee().stream()
                .filter(x -> x.getSkill() != Skill.MANAGER)
                .forEach(x -> x.setSalary(x.getSalary() * (1 + percentageIncrease)));
    }

    public void informingManager(String message) {
        View.printConsole(message);
    }

    public void manualAssignmentTask() {
        List<Task> freeTasksDepartment = TaskPlanner.getFreeTask().stream()
                .filter(x -> x.getSkill() == department.getSkill())
                .toList();
        View.printConsole(freeTasksDepartment.toString());
        int taskID = inputId("Task ID");
        List<Task> tasks = freeTasksDepartment.stream()
                .filter(x -> x.getId() == taskID)
                .toList();
        if (tasks.isEmpty()) {
            System.out.println("Invalid value");
            return;
        }
        Task task = tasks.get(0);
        View.printConsole(department.getDepartmentEmployee().toString());
        int taskIDEmployee = inputId("Employee ID");
        List<Employee> employees = department.getDepartmentEmployee().stream()
                .filter(x -> x.getId() == taskIDEmployee)
                .toList();
        if (employees.isEmpty()) {
            System.out.println("Invalid value");
            return;
        }
        Employee employee = employees.get(0);
        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        View.informingEmployee(employee, task.getPriority());
    }

    public int inputId(String message) {
        int id;
        try {
            id = Integer.parseInt(Input.inputPane(message));
        } catch (NumberFormatException | NullPointerException e) {
            View.printConsole("Invalid input");
            id = inputId(message);
        }
        return id;
    }

}
