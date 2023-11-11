package christmas.domain.model;

import christmas.domain.model.menu.Serving;
import christmas.domain.model.menu.Menu;

public class Order {

    private final Menu menu;
    private final Serving serving;

    public Order(Menu menu, Serving serving) {
        this.menu = menu;
        this.serving = serving;
    }

    public int getOrderAmount() {
        return menu.getPrice() * getServing();
    }

    public Menu getMenu() {
        return menu;
    }

    public int getServing() {
        return serving.getValue();
    }
}
