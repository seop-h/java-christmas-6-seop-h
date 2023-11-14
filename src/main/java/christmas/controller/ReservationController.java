package christmas.controller;

import christmas.controller.handler.DateHandler;
import christmas.controller.handler.OrderHandler;
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

        int date = DateHandler.process();
        Map<String, Integer> orders = OrderHandler.process();

        return reservationMaker.execute(date, orders);
    }

}
