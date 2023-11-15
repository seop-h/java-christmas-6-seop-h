package christmas.domain.service.event;

import static christmas.domain.model.date.DateConst.CHRISTMAS;
import static christmas.domain.model.event.Event.CHRISTMAS_D_DAY;
import static christmas.domain.model.event.Event.GIVEAWAY;
import static christmas.domain.model.event.Event.SPECIAL;
import static christmas.domain.model.event.Event.WEEKDAY;
import static christmas.domain.model.event.Event.WEEKEND;
import static christmas.domain.model.event.EventLimitConst.MINIMUM_POSSIBLE_EVENT;
import static christmas.domain.model.event.EventLimitConst.MINIMUM_POSSIBLE_GIVEAWAY;
import static christmas.domain.model.order.menu.Type.DESSERT;
import static christmas.domain.model.order.menu.Type.MAIN;

import christmas.domain.model.Reservation;
import christmas.domain.model.event.Event;
import christmas.domain.model.event.EventAmountConst;
import christmas.domain.model.event.EventDetail;
import christmas.domain.repository.EventRepository;
import christmas.domain.repository.PromotionStore;
import java.util.ArrayList;
import java.util.List;

public class EventApplyService {

    private final Reservation reservation;
    private final EventRepository eventRepository;

    public EventApplyService() {
        this.reservation = PromotionStore.getReservation();
        this.eventRepository = PromotionStore.getEventRepository();
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

    public String getGiveaway() {
        if (eventRepository.contains(GIVEAWAY)) {
            return EventAmountConst.GIVEAWAY_PRODUCT.toString();
        }
        return null;
    }

    public List<String> giveEventDetails() {
        List<String> result = new ArrayList<>();

        eventRepository.findALl().stream()
                .forEach(eventDetail -> result.add(eventDetail.toString()));

        return result;
    }

    private void applyChristmasDDay() {
        int date = reservation.getDateValue();
        if (date <= CHRISTMAS) {
            addEventToRepository(CHRISTMAS_D_DAY, CHRISTMAS_D_DAY.calculateBenefitAmount(date));
        }
    }

    private void applyEitherOne() {
        if (reservation.isDateWeekend()) {
            applyWeekend();
            return;
        }
        applyWeekday();
    }

    private void applySpecial() {
        if (reservation.isDateSpecialDay()) {
            addEventToRepository(SPECIAL, SPECIAL.calculateBenefitAmount(null));
        }
    }

    private void applyGiveaway() {
        if (reservation.calculateTotalOrderAmount() >= MINIMUM_POSSIBLE_GIVEAWAY) {
            addEventToRepository(GIVEAWAY, GIVEAWAY.calculateBenefitAmount(null));
        }
    }

    private void applyWeekend() {
        int servings = reservation.countServingsOf(MAIN);
        if (servings > 0) {
            addEventToRepository(WEEKEND, WEEKEND.calculateBenefitAmount(servings));
        }
    }

    private void applyWeekday() {
        int servings = reservation.countServingsOf(DESSERT);
        if (servings > 0) {
            addEventToRepository(WEEKDAY, WEEKDAY.calculateBenefitAmount(servings));
        }
    }

    private void addEventToRepository(Event event, int discount) {
        EventDetail eventDetail = new EventDetail(event, discount);
        eventRepository.add(eventDetail);
    }
}
