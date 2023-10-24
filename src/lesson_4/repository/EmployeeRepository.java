package lesson_4.repository;

import lesson_4.model.Employee;

import java.util.ArrayList;
import java.util.List;

abstract public class EmployeeRepository {

    static private List<Employee> employees;

    static public void addEmployee(Employee employee) {
        if (employees == null) employees = new ArrayList<>();
        employees.add(employee);
    }

    static public List<Employee> getEmployees() {
        return employees;
    }
}
