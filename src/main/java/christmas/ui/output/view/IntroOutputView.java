package christmas.ui.output.view;

import static christmas.ui.output.OutputMessage.EVENT_OVERVIEW_PREFIX;
import static christmas.ui.output.OutputMessage.EVENT_OVERVIEW_SUFFIX;
import static christmas.ui.output.OutputMessage.INTRODUCE_PROMOTION;

public class IntroOutputView {

    public static void printIntroduction() {
        System.out.println(INTRODUCE_PROMOTION);
    }

    public static void printEventOverview(int date) {
        System.out.println(EVENT_OVERVIEW_PREFIX + date + EVENT_OVERVIEW_SUFFIX);
        System.out.println();
    }

}
