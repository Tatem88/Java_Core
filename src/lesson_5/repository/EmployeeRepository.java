package lesson_5.repository;

import lesson_5.model.Employee;
import lesson_5.model.Skill;

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
    public static void setEmployees(List<Employee> employees) {
        EmployeeRepository.employees = employees;
    }
    public static Employee getEmployeeById (int id){
        for (Employee employee:employees){
            if (employee.getId() == id)
                return employee;
        }
        return new Employee(Skill.NO_SKILL);
    }
}
