package christmas.domain.service.reservation;

import christmas.domain.Validator;
import christmas.domain.model.Reservation;
import christmas.domain.model.date.Date;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Serving;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;
import static christmas.domain.Validator.checkCondition;

public class ReservationService {

    public Reservation makeReservation(int dateInput, Map<String, Integer> orderInput) {
        Date date = new Date(dateInput);

        Map<Menu, Order> orders = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : orderInput.entrySet()) {
            Menu matchMenu = Menu.findMatch(entry.getKey());
            validate(matchMenu); //TODO findMatch로 이동
            Serving serving = new Serving(entry.getValue());

            Order order = new Order(matchMenu, serving);
            orders.put(matchMenu, order);
        }

        return new Reservation(date, orders);
    }

    private void validate(Menu matchMenu) {
        checkCondition(matchMenu == null,
                NOT_INVALID_ORDER);
    }

}
