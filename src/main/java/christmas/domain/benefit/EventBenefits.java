package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;
import christmas.utils.Constants;

import java.util.Collections;
import java.util.Map;

public class EventBenefits {
    private final Map<Event, Integer> benefitDetails;

    public EventBenefits(Map<Event, Integer> benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public int calculateTotalBenefitAmount() {
        return benefitDetails.values()
                .stream()
                .reduce(Constants.INITIAL_AMOUNT, Integer::sum);
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
