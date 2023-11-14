package christmas.controller;

import christmas.domain.model.Reservation;
import christmas.ui.output.view.IntroOutputView;
import christmas.ui.output.view.OrderOutputView;

public class OrderController {

    private final Reservation reservation;

    public OrderController(Reservation reservation) {
        this.reservation = reservation;
    }

    public void showAllOrderMenu() {
        IntroOutputView.printEventOverview(reservation.getDateValue());

        OrderOutputView.printMenu(reservation.getOrderDetails());
    }
}
