package christmas.domain.model;

import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;
import static christmas.domain.Validator.checkCondition;
import static christmas.domain.model.order.OrderConst.MAXIMUM_TOTAL_DISH;
import static christmas.domain.model.order.menu.Type.BEVERAGE;

import christmas.domain.model.date.Date;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reservation {

    private final Date date;
    private final Map<Menu, Order> orders;

    public Reservation(Date date, Map<Menu, Order> orders) {
        validate(orders);
        this.date = date;
        this.orders = orders;
    }

    public boolean isDateWeekend() {
        return date.isWeekend();
    }

    public boolean isDateSpecialDay() {
        return date.isSpecialDay();
    }

    public int getDateValue() {
        return date.getValue();
    }

    public int countServingsOf(Type type) {
        return orders.values().stream()
                .filter(order -> order.isMenuKindOf(type))
                .mapToInt(Order::getServingValue)
                .sum();
    }

    public int calculateTotalOrderAmount() {
        return orders.values().stream()
                .mapToInt(Order::calculateOrderAmount)
                .sum();
    }

    public List<String> getOrderDetails() {
        List<String> result = new ArrayList<>();

        for (Order order : orders.values()) {
            result.add(order.toString());
        }

        return result;
    }

    private void validate(Map<Menu, Order> orders) {
        checkCondition(orders.keySet().stream()
                        .allMatch(menu -> menu.isKindOf(BEVERAGE)),
                NOT_INVALID_ORDER);
        checkCondition(orders.values().stream()
                        .mapToInt(Order::getServingValue)
                        .sum() > MAXIMUM_TOTAL_DISH,
                NOT_INVALID_ORDER);
    }


}
