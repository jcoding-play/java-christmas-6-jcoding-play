package christmas.domain.event;

import christmas.domain.VisitDate;

import java.util.Collections;
import java.util.List;

public class Events {
    private static final int MINIMUM_APPLICABLE_TOTAL_ORDER_PRICE = 10000;

    private final List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }

    public List<Event> findApplicableEvents(VisitDate visitDate, int totalOrderPrice) {
        if (totalOrderPrice < MINIMUM_APPLICABLE_TOTAL_ORDER_PRICE) {
            return Collections.emptyList();
        }
        return findApplicableEvent(visitDate, totalOrderPrice);
    }

    private List<Event> findApplicableEvent(VisitDate visitDate, int totalOrderPrice) {
        return events.stream()
                .filter(event -> event.isApplicable(visitDate, totalOrderPrice))
                .toList();
    }
}
