package christmas.domain.model.event;

import christmas.constant.UnitConst;
import christmas.util.AmountFormatter;

import static christmas.constant.UnitConst.MINUS;
import static christmas.constant.UnitConst.WON;

public class EventDetail {

    private final Event event;
    private final int discount;

    public EventDetail(Event event, int discount) {
        this.event = event;
        this.discount = discount;
    }

    public boolean isKindOf(Event event) {
        return (event.equals(event));
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return event.getDescription() + ": " + MINUS + AmountFormatter.format(discount) + WON;
    }
}
