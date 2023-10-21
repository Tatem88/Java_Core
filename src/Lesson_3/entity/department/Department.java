package Lesson_3.entity.department;

import Lesson_3.enums.Skill;
import Lesson_3.entity.person.Employee;

public class Department {
    private String name;
    private Employee manager;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        if (!manager.getSkill().equals(Skill.MANAGER)) throw new RuntimeException("Employee not manager!");
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employeeManager='" + manager.getLastName() + " " + manager.getFirstName() + '\'' +
                '}';
    }
}