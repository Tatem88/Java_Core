package lesson_5.view;

import lesson_5.model.Employee;
import lesson_5.model.Priority;

import java.util.List;

public class View {

    public static void printConsole(String message) {
        System.out.println(message);
    }


    public static void informingEmployee(Employee employee, Priority priority) {
        View.printConsole("For " + employee + " a new task with priority is assigned " + priority);
    }

    public static <T> void printConsoleList(List<T> list) {
        list.forEach(System.out::println);
    }

    public static void help() {
        printConsole("HELP");
        printConsole("EXIT");
        printConsole("GET A");
        printConsole("GET FT");
        printConsole("GET E");
        printConsole("GET AE");
        printConsole("GET ADE");
        printConsole("GET CT");
        printConsole("CREATE E");
        printConsole("CREATE T");
        printConsole("TASK M");
        printConsole("EMP S");
    }

    public static void helpEmployee() {
        printConsole("HELP");
        printConsole("EXIT ");
        printConsole("EMP ST");
        printConsole("EMP FT");
        printConsole("EMP HT");
        printConsole("EMP LT");

    }
}
