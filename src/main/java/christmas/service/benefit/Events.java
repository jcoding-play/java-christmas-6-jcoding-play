package christmas.service.benefit;

import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.order.Orders;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Events {
    private static final int MINIMUM_APPLICABLE_TOTAL_ORDER_AMOUNT = 10000;

    private final List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }

    public Map<Event, Integer> checkApplicableBenefitDetails(VisitDate visitDate, Orders orders) {
        if (orders.calculateTotalOrderAmount() < MINIMUM_APPLICABLE_TOTAL_ORDER_AMOUNT) {
            return Collections.emptyMap();
        }
        return checkEvents(visitDate, orders);
    }

    private Map<Event, Integer> checkEvents(VisitDate visitDate, Orders orders) {
        List<Event> applicableEvents = findApplicableEvents(visitDate, orders);
        return calculateEachDiscountedAmountOf(applicableEvents, visitDate, orders);
    }

    protected List<Event> findApplicableEvents(VisitDate visitDate, Orders orders) {
        return events.stream()
                .filter(event -> event.isApplicable(visitDate, orders))
                .toList();
    }

    private Map<Event, Integer> calculateEachDiscountedAmountOf(List<Event> applicableEvents, VisitDate visitDate, Orders orders) {
        return applicableEvents.stream()
                .collect(Collectors.toMap(
                        event -> event,
                        event -> event.calculateDiscountedAmount(visitDate, orders)));
    }
}
