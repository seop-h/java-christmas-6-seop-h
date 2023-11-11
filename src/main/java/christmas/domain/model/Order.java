package christmas.domain.model;

import christmas.domain.model.menu.Dish;
import christmas.domain.model.menu.Menu;

public class Order {

    private final Menu menu;
    private final Dish dish;

    public Order(Menu menu, Dish dish) {
        this.menu = menu;
        this.dish = dish;
    }

    public int getOrderAmount() {
        return menu.getPrice() * getDish();
    }

    public Menu getMenu() {
        return menu;
    }

    public int getDish() {
        return dish.getValue();
    }
}
