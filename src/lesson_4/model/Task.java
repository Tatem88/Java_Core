package lesson_4.model;

import java.time.LocalDate;

public class Task {
    private final int id;
    private String name;
    private LocalDate createDate;
    private LocalDate dueDate;
    private Priority priority;
    private Skill skill;
    private final int length;
    private static int count = 0;

    public Task(String name, int rank, LocalDate dueDate, Skill skill, int length) {
        this.name = name;
        this.createDate = LocalDate.now();
        this.dueDate = dueDate;
        this.skill = skill;
        this.priority = Priority.P2;
        this.length = length;
        this.id = count++;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getLength() {
        return length;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id = " + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", skill=" + skill +
                '}';
    }
}
