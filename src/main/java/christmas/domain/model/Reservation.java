package christmas.domain.model;

import christmas.domain.model.menu.Menu;
import christmas.domain.model.menu.Type;

import java.util.Map;

import static christmas.domain.Validator.checkCondition;
import static christmas.domain.constant.ErrorMessage.NOT_INVALID_ORDER;
import static christmas.domain.constant.OrderConst.MAXIMUM_TOTAL_DISH;
import static christmas.domain.model.menu.Type.BEVERAGE;

public class Reservation {

    private final Date date;
    private final Map<Menu, Order> orders;

    public Reservation(Date date, Map<Menu, Order> orders) {
        validate(orders);
        this.date = date;
        this.orders = orders;
    }

    public Date getDate() {
        return date;
    }

    public int calculateDishesOf(Type type) {
        return orders.values().stream()
                .filter(order -> order.getMenu().getType().equals(type))
                .mapToInt(Order::getServing)
                .sum();
    }

    public int getTotalOrderAmount() {
        return orders.values().stream()
                .mapToInt(Order::getOrderAmount)
                .sum();
    }

    private void validate(Map<Menu, Order> orders) {
        checkCondition(orders.keySet().stream()
                        .allMatch(menu -> menu.getType().equals(BEVERAGE)),
                NOT_INVALID_ORDER);
        checkCondition(orders.values().stream()
                        .mapToInt(Order::getServing)
                        .sum() > MAXIMUM_TOTAL_DISH,
                NOT_INVALID_ORDER);
    }


}
