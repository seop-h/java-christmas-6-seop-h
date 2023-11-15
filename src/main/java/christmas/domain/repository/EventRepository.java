package christmas.domain.repository;

import christmas.domain.model.event.EventDetail;

import java.util.LinkedHashSet;
import java.util.Set;

public class EventRepository {

    private final Set<EventDetail> eventDetails = new LinkedHashSet<>();

    public void add(EventDetail eventDetail) {
        eventDetails.add(eventDetail);
    }

    public Set<EventDetail> findALl() {
        return eventDetails;
    }

}
