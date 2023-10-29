package lesson_5.services;

import lesson_5.model.Assigment;
import lesson_5.model.Department;
import lesson_5.model.Employee;
import lesson_5.model.Task;
import lesson_5.repository.AssigmentRepository;
import lesson_5.repository.EmployeeRepository;
import lesson_5.view.View;

import java.util.List;

public class StartFinishService {


    FileService fileService;

    public StartFinishService() {
        this.fileService = new FileService();
    }

    public void init() {

        int maxIdEmployee = -1;
        int maxIdTask = -2;

        EmployeeRepository.setEmployees(fileService.fileReaderEmployee());

        for (Employee employee : EmployeeRepository.getEmployees()) {
            if (employee.getId() > maxIdEmployee)
                maxIdEmployee = employee.getId();
            Department department = employee.getDepartment();
            department.addEmployee(employee);
        }
        List<Task> tasks = fileService.fileReaderTask();
        for (Task task : tasks) {
            int idEmployee = task.getIdEmployee();
            int idTask = task.getId();
            if (idTask > maxIdTask)
                maxIdTask = idTask;
            if (idEmployee == -1) {
                TaskPlanner.freeTask.add(task);
            } else {
                Employee employee = EmployeeRepository.getEmployeeById(idEmployee);
                Assigment assigment = new Assigment(employee, task);
                AssigmentRepository.addAssigment(assigment);
            }
            Task.initCount(maxIdTask);
            Employee.initCount(maxIdEmployee);

        }
    }

    public void finish() {
        fileService.fileWriterAssigmentAndTask(AssigmentRepository.getAssigmentList(), TaskPlanner.getFreeTask());
        fileService.fileWriterEmployee(EmployeeRepository.getEmployees());
        View.printConsole("EXIT");

    }
}
