package christmas.domain;

public class Validator {

    public static void checkCondition(boolean condition, String errorMessage) {
        if (condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
