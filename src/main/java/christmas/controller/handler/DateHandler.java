package christmas.controller.handler;

import christmas.controller.util.Conversion;
import christmas.ui.input.view.InputView;

public class DateHandler {

    public static int process() {
        String input;
        Integer result;

        do {
            input = InputView.readDate();
        } while ((result = changeInput(input)) == null);

        return result;
    }

    private static Integer changeInput(String input) {
        try {
            return Conversion.toInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
