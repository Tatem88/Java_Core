package lesson_5.services;

import lesson_5.model.Assigment;
import lesson_5.model.Employee;
import lesson_5.model.Skill;
import lesson_5.model.Status;
import lesson_5.repository.AssigmentRepository;
import lesson_5.repository.EmployeeRepository;
import lesson_5.view.View;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {

    public List<Assigment> getAssigmentsByEmployee(Employee employee) {
        return AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee() == employee)
                .toList();
    }

    public List<Assigment> getAssigmentsByIdEmployee() {
        ScannerService scannerService = new ScannerService();
        Employee employee = EmployeeRepository.getEmployeeById(scannerService.intScanner("Input employee ID"));
        if (employee.getSkill() == Skill.NO_SKILL)
            System.out.println("Invalid value");
        return getAssigmentsByEmployee(employee);
    }



    public void startTaskByEmployee(Employee employee) {
        if (!checkingEmployeeHasCompletedTasks(employee).isEmpty()) {
            View.printConsole("The time allotted for a task is running out");
            return;
        }
        List<Assigment> assigmentsSort = getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getStatus() != Status.COMPLETE)
                .sorted(Comparator.comparingInt(x -> x.getTask().getPriority().getPriority()))
                .toList();
        if (assigmentsSort.isEmpty()) {
            View.printConsole("The list of tasks is empty");
            return;
        }
        employee.setWorking(true);
        Assigment assigment = assigmentsSort.get(0);
        assigment.getTask().setFactStartDate(LocalDate.now());
        assigment.getTask().setStatus(Status.IN_PROGRESS);

    }

    public List<Assigment> checkingEmployeeHasCompletedTasks(Employee employee) {
        return getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getStatus() == Status.IN_PROGRESS)
                .toList();
    }


    public void finishTaskByEmployee(Employee employee) {
        List<Assigment> assigmentsSort = checkingEmployeeHasCompletedTasks(employee);
        if (assigmentsSort.isEmpty()) {
            View.printConsole("No tasks at work");
            return;
        }
        Assigment assigment = assigmentsSort.get(0);
        employee.setWorking(false);
        assigment.getTask().setFactEndDate(LocalDate.now());
        assigment.getTask().setStatus(Status.COMPLETE);
    }


    public void onHoldCurrentTask(Employee employee) {
        for (Assigment progressTask : checkingEmployeeHasCompletedTasks(employee))
            progressTask.getTask().setStatus(Status.ON_HOLD);
    }
}