package christmas.controller;

import christmas.controller.util.Conversion;
import christmas.domain.model.Reservation;
import christmas.domain.service.reservation.ReservationMaker;
import christmas.ui.input.view.InputView;
import christmas.ui.output.view.IntroOutputView;

import java.util.Map;

public class ReservationController {

    private final ReservationMaker reservationMaker = new ReservationMaker();

    public Reservation initReservation() {
        IntroOutputView.printIntroduction();

        int date = initDate();
        Map<String, Integer> orders = initOrders();

        return reservationMaker.execute(date, orders);
    }

    private int initDate() {
        String date = InputView.readDate();
        return Conversion.toInt(date);
    }

    private Map<String, Integer> initOrders() {
        String orders = InputView.readMenu();
        return Conversion.toMap(orders);
    }

}
