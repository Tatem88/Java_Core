package Lesson_3;

import Lesson_3.entity.assigment.Assigment;
import Lesson_3.entity.department.Department;
import Lesson_3.enums.Priority;
import Lesson_3.enums.Skill;
import Lesson_3.entity.person.Employee;
import Lesson_3.entity.person.Manager;
import Lesson_3.planner.TaskPlanner;
import Lesson_3.repository.EmployeeRepository;
import Lesson_3.services.EmployeeService;
import Lesson_3.services.ManagerService;
import Lesson_3.entity.task.Task;

import java.time.LocalDate;


public class Main {
        static Department department;
        static Employee engineer1;
        static Employee engineer2;
        static Manager manager;
        static Task taskLow1;
        static Task taskLow2;
        static Task taskMiddle1;
        static Task taskMiddle2;
        static Task taskHigh1;
        static Task taskHigh2;


        public static void main(String[] args) {
                department = new Department("department_1");
                createEmployees();
                department.setManager(manager);
                createTasks();
                EmployeeService employeeService = new EmployeeService();

                Assigment assigmentTaskLow1 = TaskPlanner.planTask(taskLow1);
                employeeService.startTaskByEmployee(engineer1, assigmentTaskLow1);

                Assigment assigmentTaskLow2 = TaskPlanner.planTask(taskLow2);
                employeeService.startTaskByEmployee(engineer2, assigmentTaskLow2);

                Assigment assigmentTaskLow3 = TaskPlanner.planTask(taskMiddle1);
                employeeService.startTaskByEmployee(engineer1, assigmentTaskLow3);

                Assigment assigmentTaskLow4 = TaskPlanner.planTask(taskMiddle2);
                employeeService.startTaskByEmployee(engineer2, assigmentTaskLow4);

                Assigment assigmentTaskLow5 = TaskPlanner.planTask(taskHigh1);
                employeeService.startTaskByEmployee(manager, assigmentTaskLow5);

                employeeService.finishTaskByEmployee(engineer1, assigmentTaskLow5);

                System.out.println(employeeService.getAssigmentsByEmployee(engineer1));
                System.out.println(employeeService.getAssigmentsByEmployee(engineer2));
                System.out.println(employeeService.getAssigmentsByEmployee(manager));

                ManagerService managerService = new ManagerService();
                managerService.increaseSalaryByManagerAllEmployees(manager, 100);
                System.out.println(EmployeeRepository.getEmployees());
        }

        public static void createEmployees() {
                engineer1 = new Employee("Munz", "Ilia", LocalDate.of(1988, 8, 17)
                        , 500
                        , department,
                        Skill.ENGINEER);
                EmployeeRepository.addEmployee(engineer1);
                engineer2 = new Employee("Grach"
                        , "Lila"
                        , LocalDate.of(1990, 10, 10)
                        , 400
                        , department,
                        Skill.ENGINEER);
                EmployeeRepository.addEmployee(engineer2);
                manager = new Manager("Rovov"
                        , "Alex"
                        , LocalDate.of(1980, 3, 7)
                        , 2000
                        , department,
                        Skill.MANAGER);
                EmployeeRepository.addEmployee(manager);
        }

        public static void createTasks() {

                taskLow1 = new Task("low_task_level"
                        , 1,
                        LocalDate.of(2023, 10, 20)
                        , Skill.ENGINEER
                        , 5);

                taskLow2 = new Task("low_task_level_2"
                        , 2,
                        LocalDate.of(2023, 10, 21)
                        , Skill.ENGINEER
                        , 13);

                taskMiddle1 = new Task("average_task_level"
                        , 2,
                        LocalDate.of(2023, 10, 22)
                        , Skill.ENGINEER
                        , 15);
                taskMiddle1.setPriority(Priority.MIDDLE);

                taskMiddle2 = new Task("average_task_level_2"
                        , 3,
                        LocalDate.of(2023, 10, 25)
                        , Skill.ENGINEER
                        , 15);
                taskMiddle2.setPriority(Priority.MIDDLE);

                taskHigh1 = new Task("high_level_of_tasks"
                        , 3,
                        LocalDate.of(2023, 10, 23)
                        , Skill.ENGINEER
                        , 5);
                taskHigh1.setPriority(Priority.HIGH);
                taskHigh2 = new Task("Вhigh_level_of_tasks_2"
                        , 3,
                        LocalDate.of(2023, 10, 24)
                        , Skill.ENGINEER
                        , 7);
                taskHigh2.setPriority(Priority.HIGH);
        }
}