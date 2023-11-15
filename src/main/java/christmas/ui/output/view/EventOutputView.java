package christmas.ui.output.view;

import christmas.ui.output.AmountFormatter;
import christmas.ui.output.OutputMessage;

import java.util.Map;

import static christmas.ui.output.OutputMessage.EVENT_BADGE;
import static christmas.ui.output.OutputMessage.EVENT_HISTORY;
import static christmas.ui.output.OutputMessage.GIVEAWAY_MENU;
import static christmas.ui.output.OutputMessage.NOTHING;
import static christmas.ui.output.OutputMessage.TOTAL_AMOUNT_AFTER_DISCOUNT;
import static christmas.ui.output.OutputMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.ui.output.OutputMessage.UNIT;

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

    public static void printEventDetails(Map<String, Integer> details) {
        System.out.println(EVENT_HISTORY);

        if (details.size() == 0) {
            printNothing();
            return;
        }

        for (Map.Entry<String, Integer> entry : details.entrySet()) {
            System.out.println(OutputMessage.makeEventDetail(entry.getKey(), AmountFormatter.format(entry.getValue())));
        }
        System.out.println();
    }

    public static void printTotalBenefitAmount(int amount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT);
        System.out.println(OutputMessage.makeTotalBenefitAmount(AmountFormatter.format(amount)));
        System.out.println();
    }

    public static void printBadge(String badge) {
        System.out.println(EVENT_BADGE);
        System.out.println(badge);
    }

    public static void printAmountAfterDiscount(int amount) {
        System.out.println(TOTAL_AMOUNT_AFTER_DISCOUNT);
        System.out.println(AmountFormatter.format(amount) + UNIT);
        System.out.println();
    }

    private static void printNothing() {
        System.out.println(NOTHING);
        System.out.println();
    }

}
