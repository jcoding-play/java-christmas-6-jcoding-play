package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;

import java.util.Collections;
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

    public int calculateEstimatedPaymentAmount(int totalOrderAmount, int totalBenefitAmount) {
        int result = subtractBetween(totalOrderAmount, totalBenefitAmount);

        if (isGiftEventApplied()) {
            return result + benefitDetails.get(new GiftEvent());
        }
        return result;
    }

    private int subtractBetween(int totalOrderPrice, int totalBenefitAmount) {
        return totalOrderPrice - totalBenefitAmount;
    }

    public boolean isGiftEventApplied() {
        return benefitDetails.keySet()
                .stream()
                .anyMatch(Event::isGiftEvent);
    }

    public Map<Event, Integer> getBenefitDetails() {
        return Collections.unmodifiableMap(benefitDetails);
    }
}
