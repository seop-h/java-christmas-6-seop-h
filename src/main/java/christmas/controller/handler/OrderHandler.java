package christmas.controller.handler;

import christmas.controller.util.Conversion;
import christmas.ui.input.view.InputView;

import java.util.Map;

public class OrderHandler {

    public static Map<String, Integer> process() {
        String input;
        Map<String, Integer> result;

        do {
            input = InputView.readMenu();
        } while ((result = changeInput(input)) == null);

        return result;
    }

    private static Map<String, Integer> changeInput(String input) {
        try {
            return Conversion.toMap(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
