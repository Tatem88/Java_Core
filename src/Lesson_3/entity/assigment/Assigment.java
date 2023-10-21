package Lesson_3.entity.assigment;

import Lesson_3.entity.person.Employee;
import Lesson_3.entity.task.Task;
import Lesson_3.enums.Status;

import java.time.LocalDate;

public class Assigment {
    private Employee employee;
    private Task task;
    private LocalDate factStartDate;
    private LocalDate factEndDate;
    private Status status;

    public Assigment(Employee employee, Task task) {
        this.employee = employee;
        this.task = task;
        this.status = Status.NEW;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Task getTask() {
        return task;
    }

    public LocalDate getFactStartDate() {
        return factStartDate;
    }

    public void setFactStartDate(LocalDate factStartDate) {
        this.factStartDate = factStartDate;
    }

    public LocalDate getFactEndDate() {
        return factEndDate;
    }

    public void setFactEndDate(LocalDate factEndDate) {
        this.factEndDate = factEndDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Assigment{" +
                "employee=" + employee +
                ", task=" + task +
                ", factStartDate=" + factStartDate +
                ", factEndDate=" + factEndDate +
                ", status=" + status +
                '}' + "\n";
    }
}