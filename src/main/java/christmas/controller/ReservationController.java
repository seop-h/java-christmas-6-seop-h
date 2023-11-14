package christmas.controller;

import christmas.controller.handler.DateHandler;
import christmas.controller.handler.OrderHandler;
import christmas.domain.model.Reservation;
import christmas.domain.model.date.Date;
import christmas.ui.output.view.IntroOutputView;

public class ReservationController {

    public Reservation initReservation() {
        IntroOutputView.printIntroduction();

        Date date = DateHandler.process();
        Reservation reservation = OrderHandler.process(date);

        return reservation;
    }

}
