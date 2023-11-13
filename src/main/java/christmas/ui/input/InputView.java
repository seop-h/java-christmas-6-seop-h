package christmas.ui.input;

import camp.nextstep.edu.missionutils.Console;

import static christmas.ui.input.InputMessage.EXPECTED_VISIT_DATE;

public class InputView {

    public static String readDate() {
        System.out.println(EXPECTED_VISIT_DATE);
        String input = Console.readLine();
        return input;
    }

}
