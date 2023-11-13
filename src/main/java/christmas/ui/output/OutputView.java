package christmas.ui.output;

import java.text.DecimalFormat;
import java.util.Map;

import static christmas.ui.output.OutputMessage.GIVEAWAY_MENU;
import static christmas.ui.output.OutputMessage.INTRODUCE_PROMOTION;
import static christmas.ui.output.OutputMessage.ORDER_MENU;
import static christmas.ui.output.OutputMessage.TOTAL_AMOUNT_BEFORE_DISCOUNT;
import static christmas.ui.output.OutputMessage.UNIT;

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

    public static void printAmountBeforeDiscount(int amount) {
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT);
        System.out.println(format(amount) + UNIT);
        System.out.println();
    }

    public static void printGiveawayMenu(String menu, int count) {
        System.out.println(GIVEAWAY_MENU);
        System.out.println(OutputMessage.makeMenuDetail(menu, count));
        System.out.println();
    }

    private static String format(int input) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(input);
    }

}
