package christmas.domain.event;

import java.util.List;

public class Events {
    private final List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }

    public List<Event> findApplicableEvents(int date, int totalOrderPrice) {
        return events.stream()
                .filter(event -> event.isApplicable(date, totalOrderPrice))
                .toList();
    }
}
