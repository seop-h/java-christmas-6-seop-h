package christmas;

import christmas.controller.EventController;
import christmas.controller.OrderController;
import christmas.controller.ReservationController;
import christmas.domain.model.Reservation;
import christmas.domain.service.event.EventService;

import javax.swing.plaf.BorderUIResource;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Reservation reservation = new ReservationController().initReservation();

        OrderController orderController = new OrderController(reservation);
        EventController eventController = new EventController(new EventService(reservation));

        orderController.showAllOrderMenu();
        orderController.showTotalAmountBeforeDiscount();

        eventController.showAppliedEvent();
        eventController.showBadge();
    }
}
