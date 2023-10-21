package Lesson_3.enums;

public enum Priority {
    LOW(1),
    MIDDLE(2),
    HIGH(3),
    VERY_HIGH(4);
    private int code;
    Priority(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}