package christmas.ui.output.view;

import christmas.ui.output.AmountFormatter;
import christmas.ui.output.OutputMessage;

import java.util.Map;

import static christmas.ui.output.OutputMessage.ORDER_MENU;
import static christmas.ui.output.OutputMessage.TOTAL_AMOUNT_AFTER_DISCOUNT;
import static christmas.ui.output.OutputMessage.TOTAL_AMOUNT_BEFORE_DISCOUNT;
import static christmas.ui.output.OutputMessage.UNIT;

public class OrderOutputView {

    public static void printMenu(Map<String, Integer> orders) {
        System.out.println(ORDER_MENU);

        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            System.out.println(OutputMessage.makeMenuDetail(entry.getKey(), entry.getValue()));
        }
        System.out.println();
    }

    public static void printAmountBeforeDiscount(int amount) {
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT);
        System.out.println(AmountFormatter.format(amount) + UNIT);
        System.out.println();
    }

    public static void printAmountAfterDiscount(int amount) {
        System.out.println(TOTAL_AMOUNT_AFTER_DISCOUNT);
        System.out.println(AmountFormatter.format(amount) + UNIT);
        System.out.println();
    }

}
