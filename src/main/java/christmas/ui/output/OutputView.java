package christmas.ui.output;

import java.util.Map;

import static christmas.ui.output.OutputMessage.INTRODUCE_PROMOTION;
import static christmas.ui.output.OutputMessage.ORDER_MENU;

public class OutputView {

    public static void printIntroduction() {
        System.out.println(INTRODUCE_PROMOTION);
    }

    public static void printEventOverview(int date) {
        System.out.println(OutputMessage.makeEventOverview(date));
        System.out.println();
    }

    public static void printMenu(Map<String, Integer> orders) {
        System.out.println(ORDER_MENU);

        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            System.out.println(OutputMessage.makeMenuDetail(entry.getKey(), entry.getValue()));
        }
        System.out.println();
    }

}
