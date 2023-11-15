package christmas;

import christmas.controller.EventController;
import christmas.controller.OrderController;
import christmas.controller.ReservationController;

public class Application {
    public static void main(String[] args) {
        ReservationController reservationController = new ReservationController();
        reservationController.initReservation();

        OrderController orderController = new OrderController();
        EventController eventController = new EventController();

        orderController.showAllOrderMenu();
        orderController.showTotalAmountBeforeDiscount();

        eventController.showAppliedEvent();
        eventController.showBadge();
    }
}
