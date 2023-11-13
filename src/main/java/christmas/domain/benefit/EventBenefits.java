package christmas.domain.benefit;

import christmas.domain.event.Event;

import java.util.Map;

public class EventBenefits {
    private static final int INITIAL_BENEFIT_AMOUNT = 0;

    private final Map<Event, Integer> benefitDetails;

    public EventBenefits(Map<Event, Integer> benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public int calculateTotalBenefitAmount() {
        return benefitDetails.values()
                .stream()
                .reduce(INITIAL_BENEFIT_AMOUNT, Integer::sum);
    }

    public int calculateEstimatedPaymentAmount(int totalOrderPrice, int totalBenefitAmount) {
        int result = subtractBetween(totalOrderPrice, totalBenefitAmount);

        if (isGiftEventApplied()) {
            return result + 25000;
        }
        return result;
    }

    private boolean isGiftEventApplied() {
        return benefitDetails.keySet()
                .stream()
                .anyMatch(Event::isGiftEvent);
    }

    private int subtractBetween(int totalOrderPrice, int totalBenefitAmount) {
        return totalOrderPrice - totalBenefitAmount;
    }

    public Map<Event, Integer> getBenefitDetails() {
        return benefitDetails;
    }
}
