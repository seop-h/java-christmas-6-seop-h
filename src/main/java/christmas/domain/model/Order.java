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
}
