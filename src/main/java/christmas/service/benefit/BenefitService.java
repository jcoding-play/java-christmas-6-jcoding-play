package christmas.service.benefit;

import christmas.domain.benefit.BenefitDetails;
import christmas.domain.VisitDate;
import christmas.domain.benefit.EventBadge;
import christmas.domain.event.Event;
import christmas.domain.event.Events;
import christmas.domain.order.Order;

import java.util.Map;

public class BenefitService {
    private final Events events;

    public BenefitService(Events events) {
        this.events = events;
    }

    public BenefitDetails checkApplicableBenefitDetails(VisitDate visitDate, Order order) {
        Map<Event, Integer> benefitDetails = events.checkApplicableEvents(visitDate, order);

        return new BenefitDetails(benefitDetails);
    }

    public EventBadge checkEventBadge(int totalBenefitAmount) {
        return EventBadge.of(totalBenefitAmount);
    }
}
