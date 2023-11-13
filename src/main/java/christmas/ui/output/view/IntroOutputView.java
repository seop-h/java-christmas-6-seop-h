package christmas.ui.output.view;

import christmas.ui.output.OutputMessage;

import static christmas.ui.output.OutputMessage.INTRODUCE_PROMOTION;

public class IntroOutputView {

    public static void printIntroduction() {
        System.out.println(INTRODUCE_PROMOTION);
    }

    public static void printEventOverview(int date) {
        System.out.println(OutputMessage.makeEventOverview(date));
        System.out.println();
    }

}
