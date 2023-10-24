package lesson_4.services;

import lesson_4.model.Director;
import lesson_4.model.Skill;
import lesson_4.repository.EmployeeRepository;
import lesson_4.services.exeption.CheckingAccessRights;
import lesson_4.view.View;

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
