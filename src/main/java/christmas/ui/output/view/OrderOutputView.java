package christmas.ui.output.view;

import christmas.util.AmountFormatter;

import java.util.List;

import static christmas.ui.output.OutputMessage.ORDER_MENU;
import static christmas.ui.output.OutputMessage.TOTAL_AMOUNT_BEFORE_DISCOUNT;
import static christmas.ui.output.OutputMessage.UNIT;

public class OrderOutputView {

    public static void printMenu(List<String> orderDetails) {
        System.out.println(ORDER_MENU);

        for (String orderDetail : orderDetails) {
            System.out.println(orderDetail);
        }
        System.out.println();
    }

    public static void printAmountBeforeDiscount(int amount) {
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT);
        System.out.println(AmountFormatter.format(amount) + UNIT);
        System.out.println();
    }

}
