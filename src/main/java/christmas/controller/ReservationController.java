package christmas.controller;

import christmas.controller.util.Conversion;
import christmas.ui.input.view.InputView;

import java.util.Map;

public class ReservationController {

    public int initDate() {
        String date = InputView.readDate();
        return Conversion.toInt(date);
    }

    public Map<String, Integer> initOrders() {
        String orders = InputView.readMenu();
        return Conversion.toMap(orders);
    }

}
