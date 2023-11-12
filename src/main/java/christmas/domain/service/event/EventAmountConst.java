package christmas.domain.service.event;

import christmas.domain.model.order.menu.Menu;

import static christmas.domain.model.order.menu.Menu.CHAMPAGNE;

public abstract class EventAmountConst {

    public static final int D_DAY_START = 900;
    public static final int D_DAY_INCREMENT = 100;
    public static final int MENU_DISCOUNT = 2_023;
    public static final int SPECIAL_DISCOUNT = 1_000;

    public static final int MINIMUM_POSSIBLE_GIVEAWAY = 120_000;
    public static final Menu GIVEAWAY_PRODUCT = CHAMPAGNE;

}
