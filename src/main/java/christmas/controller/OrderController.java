package christmas.controller;

import christmas.domain.model.Reservation;
import christmas.domain.repository.PromotionStore;
import christmas.ui.output.view.IntroOutputView;
import christmas.ui.output.view.OrderOutputView;

public class OrderController {

    private final Reservation reservation;

    public OrderController() {
        this.reservation = PromotionStore.getReservation();
    }

    public void showAllOrderMenu() {
        IntroOutputView.printEventOverview(reservation.getDateValue());

        OrderOutputView.printMenu(reservation.getOrderDetails());
    }

    public void showTotalAmountBeforeDiscount() {
        OrderOutputView.printAmountBeforeDiscount(reservation.calculateTotalOrderAmount());
    }
}
