package christmas.controller.handler;

import christmas.controller.util.Conversion;
import christmas.domain.model.date.Date;
import christmas.ui.input.view.InputView;

public class DateHandler {

    public static Date process() {
        String input;
        Date result;

        do {
            input = InputView.readDate();
        } while ((result = changeInput(input)) == null);

        return result;
    }

    private static Date changeInput(String input) {
        try {
            int value = Conversion.toInt(input);
            return new Date(value);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
