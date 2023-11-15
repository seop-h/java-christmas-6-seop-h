package christmas.domain.service.event;

import static christmas.domain.model.event.Badge.SANTA;
import static christmas.domain.model.event.Badge.STAR;
import static christmas.domain.model.event.Badge.TREE;
import static christmas.domain.model.event.Event.GIVEAWAY;

import christmas.domain.model.Reservation;
import christmas.domain.model.event.EventAmountConst;
import christmas.domain.model.event.EventDetail;
import christmas.domain.repository.EventRepository;
import christmas.domain.repository.PromotionStore;

public class EventCalculateService {

    private final Reservation reservation;
    private final EventRepository eventRepository;

    public EventCalculateService() {
        this.reservation = PromotionStore.getReservation();
        this.eventRepository = PromotionStore.getEventRepository();
    }

    public int calculateTotalBenefitAmount() {
        return eventRepository.findALl().stream()
                .mapToInt(EventDetail::getDiscount)
                .sum();
    }

    public int calculateExpectedPayAmount() {
        int expectedPayAmount = reservation.calculateTotalOrderAmount() - calculateTotalBenefitAmount();

        if (eventRepository.contains(GIVEAWAY)) {
            expectedPayAmount += EventAmountConst.GIVEAWAY_PRODUCT.calculateOrderAmount();
        }

        return expectedPayAmount;
    }

    public String giveBadge() {
        int amount = calculateTotalBenefitAmount();

        if (SANTA.isAmountSatisfied(amount)) {
            return SANTA.toString();
        }
        if (TREE.isAmountSatisfied(amount)) {
            return TREE.toString();
        }
        if (STAR.isAmountSatisfied(amount)) {
            return STAR.toString();
        }
        return null;
    }

}
