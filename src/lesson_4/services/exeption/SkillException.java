package lesson_4.services.exeption;

public class SkillException extends RuntimeException{

    public SkillException (){
        super("Does not correspond to the employee’s competencies");
    }
}