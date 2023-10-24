package lesson_4.services;

import lesson_4.model.Assigment;
import lesson_4.model.Employee;
import lesson_4.model.Skill;
import lesson_4.model.Task;
import lesson_4.repository.AssigmentRepository;
import lesson_4.view.View;

import java.util.ArrayList;
import java.util.List;


public class TaskPlanner {

    private final SelectionEmployee selectionEmployee;
    public static List<Task> freeTask = new ArrayList<>();


    public TaskPlanner(SelectionEmployee selectionEmployee) {
        this.selectionEmployee = selectionEmployee;
    }

    public void planTask(Task task) {
        Employee employee = selectionEmployee.selectionEmployee(task.getSkill(), task.getPriority());
        if (employee.getSkill() == Skill.MANAGER) {
            freeTask.add(task);
            return;
        }
        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        View.informingEmployee(employee, task.getPriority());
    }

    public static List<Task> getFreeTask() {
        return freeTask;
    }


}
