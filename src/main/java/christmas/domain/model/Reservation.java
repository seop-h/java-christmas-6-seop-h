package christmas.domain.model;

import christmas.domain.model.date.Date;
import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Type;
import christmas.domain.model.order.Order;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.domain.Validator.checkCondition;
import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;
import static christmas.domain.model.order.OrderConst.MAXIMUM_TOTAL_DISH;
import static christmas.domain.model.order.menu.Type.BEVERAGE;

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

    //TODO DTO 고민
    public Map<String, Integer> getOrderDetails() {
        Map<String, Integer> result = new LinkedHashMap<>();

        for (Map.Entry<Menu, Order> order : orders.entrySet()) {
            result.put(order.getKey().getName(), order.getValue().getServingValue());
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
