package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Events {
    private static final int MINIMUM_APPLICABLE_TOTAL_ORDER_PRICE = 10000;

    private final List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }

    public Map<Event, Integer> checkApplicableEvents(VisitDate visitDate, Order order) {
        List<Event> applicableEvents = findApplicableEvents(visitDate, order);

        return calculateEachDiscountedAmountOf(applicableEvents, visitDate, order);
    }

    public List<Event> findApplicableEvents(VisitDate visitDate, Order order) {
        if (order.calculateTotalOrderAmount() < MINIMUM_APPLICABLE_TOTAL_ORDER_PRICE) {
            return Collections.emptyList();
        }
        return findApplicableEvent(visitDate, order);
    }

    private List<Event> findApplicableEvent(VisitDate visitDate, Order order) {
        return events.stream()
                .filter(event -> event.isApplicable(visitDate, order))
                .toList();
    }

    private Map<Event, Integer> calculateEachDiscountedAmountOf(List<Event> applicableEvents, VisitDate visitDate, Order order) {
        return applicableEvents.stream()
                .collect(Collectors.toMap(
                        event -> event,
                        event -> event.calculateDiscountedAmount(visitDate, order)));
    }
}
