package lesson_4.view;

import lesson_4.model.Employee;
import lesson_4.model.Priority;

public class View {

    public static void printConsole(String message) {
        System.out.println(message);
    }

    /**
     * Информирование работника о поступлении нового задания.
     */
    public static void informingEmployee(Employee employee, Priority priority) {
        View.printConsole("For " + employee + " new task priority " + priority);
    }
}
