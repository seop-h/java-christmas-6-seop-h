package christmas.ui.output.view;

import christmas.util.AmountFormatter;

import java.util.List;

import static christmas.constant.UnitConst.MINUS;
import static christmas.constant.UnitConst.WON;
import static christmas.ui.output.OutputMessage.EVENT_BADGE;
import static christmas.ui.output.OutputMessage.EVENT_HISTORY;
import static christmas.ui.output.OutputMessage.GIVEAWAY_MENU;
import static christmas.ui.output.OutputMessage.NOTHING;
import static christmas.ui.output.OutputMessage.TOTAL_AMOUNT_AFTER_DISCOUNT;
import static christmas.ui.output.OutputMessage.TOTAL_BENEFIT_AMOUNT;

public class EventOutputView {

    public static void printGiveawayMenu(String giveaway) {
        System.out.println(GIVEAWAY_MENU);

        if (giveaway == null) {
            printNothing();
            return;
        }

        System.out.println(giveaway);
        System.out.println();
    }

    public static void printEventDetails(List<String> details) {
        System.out.println(EVENT_HISTORY);

        if (details.size() == 0) {
            printNothing();
            return;
        }

        for (String detail : details) {
            System.out.println(detail);
        }
        System.out.println();
    }

    public static void printTotalBenefitAmount(int amount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT);

        String format = AmountFormatter.format(amount);
        if (!format.equals("0")) {
            System.out.print(MINUS);
        }

        System.out.println(format + WON);
        System.out.println();
    }

    public static void printBadge(String badge) {
        System.out.println(EVENT_BADGE);
        System.out.println(badge);
    }

    public static void printAmountAfterDiscount(int amount) {
        System.out.println(TOTAL_AMOUNT_AFTER_DISCOUNT);
        System.out.println(AmountFormatter.format(amount) + WON);
        System.out.println();
    }

    private static void printNothing() {
        System.out.println(NOTHING);
        System.out.println();
    }

}
