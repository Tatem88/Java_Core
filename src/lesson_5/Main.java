package lesson_5;

import lesson_5.controller.UI;
import lesson_5.services.StartFinishService;

public class Main {

    public static void main(String[] args) {
        StartFinishService startFinishService = new StartFinishService();
        startFinishService.init();
        UI ui = new UI();
        ui.run();
        startFinishService.finish();
    }
}