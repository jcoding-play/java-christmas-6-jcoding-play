package christmas.service.benefit;

import christmas.domain.event.Events;

public class BenefitService {
    private final Events events;

    public BenefitService(Events events) {
        this.events = events;
    }
}
