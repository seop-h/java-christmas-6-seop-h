package christmas.domain.model.event;

import christmas.domain.model.order.Order;
import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Serving;

public abstract class EventAmountConst {

    public static final int D_DAY_START = 1_000;
    public static final int D_DAY_INCREMENT = 100;

    public static final int PER_MENU = 2_023;

    public static final int SPECIAL_AMOUNT = 1_000;

    public static final Order GIVEAWAY_PRODUCT = new Order(Menu.CHAMPAGNE, new Serving(1));

}
