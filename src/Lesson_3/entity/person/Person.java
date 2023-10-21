package Lesson_3.entity.person;


import java.time.LocalDate;

public class Person {
    protected String lastName;
    protected String firstName;
    protected LocalDate birthDate;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
