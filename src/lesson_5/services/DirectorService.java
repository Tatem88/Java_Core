package lesson_5.services;

import lesson_5.model.Director;
import lesson_5.model.Skill;
import lesson_5.repository.EmployeeRepository;
import lesson_5.services.exeption.CheckingAccessRights;
import lesson_5.view.View;

public class DirectorService {


    private DirectorService(Director director) {
    }

    public static DirectorService factoryDirectorService(Director director) {
        try {
            if (Skill.DIRECTOR == director.getSkill()) {
                return new DirectorService(director);
            } else throw new CheckingAccessRights();
        } catch (CheckingAccessRights e) {
            View.printConsole(e.getMessage());
        }
        return null;
    }

    public void raisingSalaries(double percentageIncrease) {
        EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getSkill() != Skill.DIRECTOR)
                .forEach(x -> x.setSalary(x.getSalary() * (1 + percentageIncrease)));
    }

}