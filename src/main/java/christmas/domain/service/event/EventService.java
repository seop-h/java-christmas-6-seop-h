package christmas.domain.service.event;

import christmas.domain.model.Reservation;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.domain.model.date.DateConst.CHRISTMAS;
import static christmas.domain.service.event.EventLimitConst.MINIMUM_POSSIBLE_EVENT;
import static christmas.domain.service.event.EventLimitConst.MINIMUM_POSSIBLE_GIVEAWAY;
import static christmas.domain.service.event.Badge.SANTA;
import static christmas.domain.service.event.Badge.STAR;
import static christmas.domain.service.event.Badge.TREE;
import static christmas.domain.service.event.Event.CHRISTMAS_D_DAY;
import static christmas.domain.service.event.Event.GIVEAWAY;
import static christmas.domain.service.event.Event.SPECIAL;
import static christmas.domain.service.event.Event.WEEKDAY;
import static christmas.domain.service.event.Event.WEEKEND;
import static christmas.domain.model.order.menu.Type.DESSERT;
import static christmas.domain.model.order.menu.Type.MAIN;

public class EventService {

    //TODO repository 별도로 만들기?
    private final Reservation reservation;
    private final Map<Event, Integer> eventDetails;

    public EventService(Reservation reservation) {
        this.reservation = reservation;
        this.eventDetails = new LinkedHashMap<>();
    }

    public void applyEvent() {
        if (reservation.calculateTotalOrderAmount() < MINIMUM_POSSIBLE_EVENT) {
            return;
        }

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
        int date = reservation.getDateValue();
        if (date <= CHRISTMAS) {
            eventDetails.put(CHRISTMAS_D_DAY, CHRISTMAS_D_DAY.calculateBenefitAmount(date));
        }
    }

    private void applyEitherOne() {
        if (reservation.isDateWeekend()){
            applyWeekend();
            return;
        }
        applyWeekday();
    }

    private void applySpecial() {
        if (reservation.isDateSpecialDay()) {
            eventDetails.put(SPECIAL, SPECIAL.calculateBenefitAmount(null));
        }
    }

    private void applyGiveaway() {
        if (reservation.calculateTotalOrderAmount() > MINIMUM_POSSIBLE_GIVEAWAY) {
            eventDetails.put(GIVEAWAY, GIVEAWAY.calculateBenefitAmount(null));
        }
    }

    private void applyWeekend() {
        int servings = reservation.countServingsOf(MAIN);
        if (servings > 0) {
            eventDetails.put(WEEKEND, WEEKEND.calculateBenefitAmount(servings));
        }
    }

    private void applyWeekday() {
        int servings = reservation.countServingsOf(DESSERT);
        if (servings > 0) {
            eventDetails.put(WEEKDAY, WEEKDAY.calculateBenefitAmount(servings));
        }
    }

}
