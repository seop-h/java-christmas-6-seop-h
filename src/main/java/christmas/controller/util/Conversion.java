package christmas.controller.util;

import static christmas.constant.ErrorMessage.NOT_INVALID_DATE;

public class Conversion {

    public static int toInt(String input) {
        try {
            int parseInt = Integer.parseInt(input);
            return parseInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INVALID_DATE, e);
        }
    }

}
