package lesson_4.model;

import java.time.LocalDate;

public class Employee {
    private final String lastName;
    private final String firstName;
    private final LocalDate birthDate;
    private double salary;
    private Department department;
    private Skill skill;
    private final int id;
    private static int count;

    private boolean isWorking = false;

    public Employee(String lastName, String firstName, LocalDate birthDate, double salary, Skill skill) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.skill = skill;
        this.id = count++;
    }
    public Employee(String lastName, String firstName, LocalDate birthDate, double salary, Skill skill,int id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.skill = skill;
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id= " + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", skill=" + skill +
                '}';
    }
}
