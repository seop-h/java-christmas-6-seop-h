package christmas.controller.handler;

import christmas.controller.util.Conversion;
import christmas.domain.model.Reservation;
import christmas.domain.model.date.Date;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Serving;
import christmas.ui.input.view.InputView;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderHandler {

    public static Reservation process(Date date) {
        String input;
        Reservation result;

        do {
            input = InputView.readMenu();
        } while ((result = changeInput(date, input)) == null);

        return result;
    }

    private static Reservation changeInput(Date date, String input) {
        try {
            Map<String, Integer> orderInput = Conversion.toMap(input);
            Map<Menu, Order> orders = makeOrders(orderInput);
            return new Reservation(date, orders);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Map<Menu, Order> makeOrders(Map<String, Integer> orderInput) {
        Map<Menu, Order> orders = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : orderInput.entrySet()) {
            Menu matchMenu = Menu.findMatch(entry.getKey());
            Serving serving = new Serving(entry.getValue());

            Order order = new Order(matchMenu, serving);
            orders.put(matchMenu, order);
        }

        return orders;
    }

}
