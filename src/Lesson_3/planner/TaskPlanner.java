package Lesson_3.planner;

import Lesson_3.entity.assigment.Assigment;
import Lesson_3.entity.person.Employee;
import Lesson_3.entity.task.Task;
import Lesson_3.enums.Status;
import Lesson_3.repository.AssigmentRepository;
import Lesson_3.repository.EmployeeRepository;

import java.util.List;

abstract public class TaskPlanner {
    public static Assigment planTask(Task task) {
        List<Employee> employeeList = EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getSkill() == task.getSkill())
                .filter(x -> !x.isWorking())
                .toList();
        if (!employeeList.isEmpty()) return createAssigment(employeeList.get(0), task);
        else return planPriorityTask(task);
    }

    public static Assigment planPriorityTask(Task task) {
        Employee employee = null;
        List<Assigment> assigmentList = AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee().getSkill() == task.getSkill())
                .filter(x -> x.getTask().getPriority().getCode() < task.getPriority().getCode())
                .filter(x -> {

                    List<Assigment> assigmentList1 = AssigmentRepository.getAssigmentList().stream()
                            .filter(y -> y.getEmployee() == x.getEmployee())
                            .filter(y -> y.getTask().getPriority().getCode() >= task.getPriority().getCode())
                            .filter(y -> y.getStatus().equals(Status.IN_PROGRESS))
                            .toList();
                    return assigmentList1.isEmpty();
                })
                .toList();
        if (!assigmentList.isEmpty()) {
            Assigment assigment = assigmentList.get(0);
            employee = assigment.getEmployee();
        }
        return createAssigment(employee, task);
    }

    private static Assigment createAssigment(Employee employee, Task task) {
        Assigment assigment = null;
        if (employee != null && task != null) {
            assigment = new Assigment(employee, task);
            AssigmentRepository.addAssigment(assigment);
        }
        return assigment;
    }
}
