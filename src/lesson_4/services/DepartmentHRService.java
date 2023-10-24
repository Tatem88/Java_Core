package lesson_4.services;

import lesson_4.model.Department;
import lesson_4.model.Employee;
import lesson_4.model.Skill;
import lesson_4.services.exeption.SkillException;
import lesson_4.view.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentHRService {

    public List<Department> departments;

    public void addDepartment (Department department) {
        if (departments == null) departments = new ArrayList<>();
        departments.add(department);
    }

    public void addEmployeeDepartment(Department department, Employee employee) {
        department.addEmployee(employee);
        employee.setDepartment(department);
    }

    public void appointManager(Department department, Employee manager) {
        try {
            if (manager.getSkill() == Skill.MANAGER || manager.getSkill() == Skill.DIRECTOR) {
                department.setManager(manager);
                manager.setDepartment(department);
            } else throw new SkillException();
        } catch (SkillException e) {
            View.printConsole(e.getMessage());
        }
    }
    public Employee getDepartmentManager (Skill skill){
        List<Department>  departmentSortSkill = departments.stream()
                .filter(x-> x.getSkill() == skill)
                .toList();
        try {
            return departmentSortSkill.get(0).getManager();
        }catch (ArrayIndexOutOfBoundsException e){
            return new Employee("","", LocalDate.of(1950,1,1),0,Skill.MANAGER);
        }

    }
}
