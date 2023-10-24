package lesson_4.view;

import javax.swing.*;

public class Input {

    public static String inputPane(String message) {
        return JOptionPane.showInputDialog(null, message);
    }
}