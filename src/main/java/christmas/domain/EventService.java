package christmas.domain;

import christmas.domain.constant.EventAmountConst;
import christmas.domain.model.Event;
import christmas.domain.model.Reservation;
import christmas.domain.model.menu.Type;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.domain.constant.DateConst.CHRISTMAS;
import static christmas.domain.constant.EventAmountConst.D_DAY_INCREMENT;
import static christmas.domain.constant.EventAmountConst.D_DAY_START;
import static christmas.domain.constant.EventAmountConst.MENU_DISCOUNT;
import static christmas.domain.model.Event.CHRISTMAS_D_DAY;
import static christmas.domain.model.Event.WEEKDAY;
import static christmas.domain.model.menu.Type.DESSERT;


public class EventService {

    private final Reservation reservation;
    private final Map<Event, Integer> eventDetails;

    public EventService(Reservation reservation) {
        this.reservation = reservation;
        this.eventDetails = new LinkedHashMap<>();
    }

    private void applyChristmasDDay() {
        int date = reservation.getDate().getValue();

        if (date > CHRISTMAS) {
            return;
        }

        eventDetails.put(CHRISTMAS_D_DAY, D_DAY_START + date * D_DAY_INCREMENT);
    }

    private void applyWeekday() {
        if (reservation.getDate().isWeekend()){
            return;
        }

        int dish = reservation.calculateDishesOf(DESSERT);
        eventDetails.put(WEEKDAY, dish * MENU_DISCOUNT);
    }

}
