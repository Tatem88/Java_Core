package Lesson_3.entity.task;

import Lesson_3.enums.Priority;
import Lesson_3.enums.Skill;

import java.time.LocalDate;

public class Task {
    private String name;
    private int rank;
    private LocalDate createDate;
    private LocalDate dueDate;
    private Priority priority;
    private Skill skill;
    private int length;

    public Task(String name, int rank, LocalDate dueDate, Skill skill, int length) {
        this.name = name;
        this.rank = rank;
        this.createDate = LocalDate.now();
        this.dueDate = dueDate;
        this.skill = skill;
        this.priority = Priority.LOW;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int range) {
        this.rank = range;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
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

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                ", createDate=" + createDate +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", skill=" + skill +
                ", length=" + length +
                '}';
    }
}
