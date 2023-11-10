package christmas.service.benefit;

import christmas.domain.event.Event;
import christmas.domain.event.Events;

import java.util.Collections;
import java.util.List;

public class BenefitService {
    private static final int MINIMUM_APPLICABLE_TOTAL_ORDER_PRICE = 10000;

    private final Events events;

    public BenefitService(Events events) {
        this.events = events;
    }

    public List<Event> findApplicableEvents(int date, int totalOrderPrice) {
        if (totalOrderPrice < MINIMUM_APPLICABLE_TOTAL_ORDER_PRICE) {
            return Collections.emptyList();
        }
        return events.findApplicableEvents(date, totalOrderPrice);
    }
}
