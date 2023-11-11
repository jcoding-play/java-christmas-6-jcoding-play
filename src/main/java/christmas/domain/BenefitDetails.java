package christmas.domain;

import christmas.domain.event.Event;

import java.util.Map;

public class BenefitDetails {
    private final Map<Event, Integer> benefitDetails;

    public BenefitDetails(Map<Event, Integer> benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public Map<Event, Integer> getBenefitDetails() {
        return benefitDetails;
    }
}
