package christmas.controller;

import christmas.domain.model.event.Badge;
import christmas.domain.service.event.EventApplyService;
import christmas.domain.service.event.EventCalculateService;
import christmas.ui.output.view.EventOutputView;

import java.util.List;

public class EventController {

    private final EventApplyService applyService;
    private final EventCalculateService calculateService;

    public EventController() {
        this.applyService = new EventApplyService();
        this.calculateService = new EventCalculateService();
    }

    public void showAppliedEvent() {
        applyService.applyEvent();

        String giveaway = applyService.getGiveaway();
        EventOutputView.printGiveawayMenu(giveaway);

        List<String> eventDetails = applyService.giveEventDetails();
        EventOutputView.printEventDetails(eventDetails);

        EventOutputView.printTotalBenefitAmount(calculateService.calculateTotalBenefitAmount());
        EventOutputView.printAmountAfterDiscount(calculateService.calculateExpectedPayAmount());
    }

    public void showBadge() {
        String badge = calculateService.giveBadge();
        EventOutputView.printBadge(badge);
    }

}
