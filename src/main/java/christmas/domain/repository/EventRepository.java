package christmas.domain.repository;

import christmas.domain.model.event.Event;
import christmas.domain.model.event.EventDetail;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private final List<EventDetail> eventDetails = new ArrayList<>();

    public void add(EventDetail eventDetail) {
        eventDetails.add(eventDetail);
    }

    public List<EventDetail> findALl() {
        return eventDetails;
    }

    public boolean contains(Event event) {
        return eventDetails.stream()
                .anyMatch(eventDetail -> eventDetail.isKindOf(event));
    }

}
