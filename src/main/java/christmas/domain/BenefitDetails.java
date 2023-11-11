package christmas.domain;

import christmas.domain.event.Event;

import java.util.Map;

public class BenefitDetails {
    private static final int INITIAL_BENEFIT_AMOUNT = 0;

    private final Map<Event, Integer> benefitDetails;

    public BenefitDetails(Map<Event, Integer> benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public int calculateTotalBenefitAmount() {
        return benefitDetails.values()
                .stream()
                .reduce(INITIAL_BENEFIT_AMOUNT, Integer::sum);
    }

    public Map<Event, Integer> getBenefitDetails() {
        return benefitDetails;
    }
}
