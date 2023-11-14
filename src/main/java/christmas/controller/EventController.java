package christmas.controller;

import christmas.domain.service.event.Badge;
import christmas.domain.service.event.Event;
import christmas.domain.service.event.EventAmountConst;
import christmas.domain.service.event.EventService;
import christmas.ui.output.OutputMessage;
import christmas.ui.output.view.EventOutputView;
import christmas.ui.output.view.OrderOutputView;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    public void showAppliedEvent() {
        service.applyEvent();

        Map<Event, Integer> eventDetails = service.getEventDetails();

        showGiveaway(eventDetails);
        showEventDetails(eventDetails);

        EventOutputView.printTotalBenefitAmount(service.calculateTotalBenefitAmount());
        OrderOutputView.printAmountAfterDiscount(service.calculateExpectedPayAmount());
    }

    private void showGiveaway(Map<Event, Integer> eventDetails) {
        String giveaway = null;
        if (eventDetails.containsKey(Event.GIVEAWAY)) {
            giveaway = EventAmountConst.GIVEAWAY_PRODUCT.toString();
            EventOutputView.printGiveawayMenu(giveaway);
        }
    }

    private void showEventDetails(Map<Event, Integer> eventDetails) {
        Map<String, Integer> eventDetailsDto = new LinkedHashMap<>();
        for (Map.Entry<Event, Integer> entry : eventDetails.entrySet()) {
            eventDetailsDto.put(entry.getKey().getDescription(), entry.getValue());
        }
        EventOutputView.printEventDetails(eventDetailsDto);
    }

}
