package lesson_5.services.exeption;

public class CheckingAccessRights extends RuntimeException{
    public CheckingAccessRights() {
        super("No access rights");
    }
}
