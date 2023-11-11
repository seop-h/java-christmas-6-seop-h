package christmas.domain.service;

import christmas.domain.model.Reservation;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.domain.model.date.DateConst.CHRISTMAS;
import static christmas.domain.service.EventAmountConst.D_DAY_INCREMENT;
import static christmas.domain.service.EventAmountConst.D_DAY_START;
import static christmas.domain.service.EventAmountConst.GIVEAWAY_PRODUCT;
import static christmas.domain.service.EventAmountConst.MENU_DISCOUNT;
import static christmas.domain.service.EventAmountConst.MINIMUM_POSSIBLE_GIVEAWAY;
import static christmas.domain.service.EventAmountConst.SPECIAL_DISCOUNT;
import static christmas.domain.service.Badge.SANTA;
import static christmas.domain.service.Badge.STAR;
import static christmas.domain.service.Badge.TREE;
import static christmas.domain.service.Event.CHRISTMAS_D_DAY;
import static christmas.domain.service.Event.GIVEAWAY;
import static christmas.domain.service.Event.SPECIAL;
import static christmas.domain.service.Event.WEEKDAY;
import static christmas.domain.service.Event.WEEKEND;
import static christmas.domain.model.order.menu.Type.DESSERT;
import static christmas.domain.model.order.menu.Type.MAIN;

public class EventService {

    private final Reservation reservation;
    private final Map<Event, Integer> eventDetails;

    public EventService(Reservation reservation) {
        this.reservation = reservation;
        this.eventDetails = new LinkedHashMap<>();
    }

    public void applyEvent() {
        applyChristmasDDay();
        applyEitherOne();
        applySpecial();
        applyGiveaway();
    }

    public Badge giveBadge() {
        int amount = calculateTotalBenefitAmount();

        if (SANTA.isAmountSatisfied(amount)) {
            return SANTA;
        }
        if (TREE.isAmountSatisfied(amount)) {
            return TREE;
        }
        if (STAR.isAmountSatisfied(amount)) {
            return STAR;
        }
        return null;
    }

    private int calculateTotalBenefitAmount() {
        return eventDetails.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void applyChristmasDDay() {
        int date = reservation.getDate().getValue();
        if (date <= CHRISTMAS) {
            eventDetails.put(CHRISTMAS_D_DAY, D_DAY_START + date * D_DAY_INCREMENT);
        }
    }

    private void applyEitherOne() {
        if (reservation.getDate().isWeekend()){
            applyWeekend();
            return;
        }
        applyWeekday();
    }

    private void applySpecial() {
        if (reservation.getDate().isSpecialDay()) {
            eventDetails.put(SPECIAL, SPECIAL_DISCOUNT);
        }
    }

    private void applyGiveaway() {
        if (reservation.calculateTotalOrderAmount() > MINIMUM_POSSIBLE_GIVEAWAY) {
            eventDetails.put(GIVEAWAY, GIVEAWAY_PRODUCT.getPrice());
        }
    }

    private void applyWeekend() {
        int dish = reservation.calculateDishesOf(MAIN);
        eventDetails.put(WEEKEND, dish * MENU_DISCOUNT);
    }

    private void applyWeekday() {
        int dish = reservation.calculateDishesOf(DESSERT);
        eventDetails.put(WEEKDAY, dish * MENU_DISCOUNT);
    }

}
