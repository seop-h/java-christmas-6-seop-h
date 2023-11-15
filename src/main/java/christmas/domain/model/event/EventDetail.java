package christmas.domain.model.event;

import christmas.util.AmountFormatter;

public class EventDetail {

    private final Event event;
    private final int discount;

    public EventDetail(Event event, int discount) {
        this.event = event;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return event.getDescription() + ": -" + AmountFormatter.format(discount) + "원";
    }
}
