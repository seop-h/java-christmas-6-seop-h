package christmas.domain.model.order;

import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Serving;
import christmas.domain.model.order.menu.Type;

public class Order {

    private final Menu menu;
    private final Serving serving;

    public Order(Menu menu, Serving serving) {
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

    @Override
    public String toString() {
        return menu.getName() + " " + getServingValue() + "개";
    }
}
