package christmas.controller.handler;

import christmas.controller.util.Conversion;
import christmas.domain.model.Reservation;
import christmas.domain.model.date.Date;
import christmas.domain.service.reservation.ReservationMaker;
import christmas.ui.input.view.InputView;

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
            return ReservationMaker.execute(date, orderInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
