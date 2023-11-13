package christmas.ui.input.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.ui.input.InputMessage.EXPECTED_VISIT_DATE;
import static christmas.ui.input.InputMessage.ORDER_MENU_AND_QUANTITY;

public class InputView {

    public static String readDate() {
        System.out.println(EXPECTED_VISIT_DATE);
        String input = Console.readLine();
        return input;
    }

    public static String readMenu() {
        System.out.println(ORDER_MENU_AND_QUANTITY);
        String input = Console.readLine();
        return input;
    }

}
