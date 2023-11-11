package christmas.domain.model.order;

import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Serving;
import christmas.domain.model.order.menu.Type;

import static christmas.domain.Validator.checkCondition;
import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;

public class Order {

    private final Menu menu;
    private final Serving serving;

    public static Order create(String menuName, int serving) {
        Menu matchMenu = Menu.findMatch(menuName);
        validate(matchMenu);
        return new Order(matchMenu, new Serving(serving));
    }

    private Order(Menu menu, Serving serving) {
        this.menu = menu;
        this.serving = serving;
    }

    public boolean isMenuKindOf(Type type) {
        return menu.isKindOf(type);
    }

    public int calculateOrderAmount() {
        return menu.getPrice() * getServingValue();
    }

    public int getServingValue() {
        return serving.getValue();
    }

    private static void validate(Menu matchMenu) {
        checkCondition(matchMenu == null,
                NOT_INVALID_ORDER);
    }
}
