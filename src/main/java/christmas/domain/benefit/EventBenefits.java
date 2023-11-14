package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;
import christmas.domain.menu.Menu;
import christmas.dto.GiftMenuDto;
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

        return result + benefitDetails.getOrDefault(new GiftEvent(), Constants.INITIAL_AMOUNT);
    }

    private int subtractBetween(int totalOrderPrice, int totalBenefitAmount) {
        return totalOrderPrice - totalBenefitAmount;
    }

    public GiftMenuDto getGiftMenu() {
        GiftEvent giftEvent = new GiftEvent();
        Menu giftMenu = giftEvent.getGiftMenu();

        if (isGiftEventApplied()) {
            return new GiftMenuDto(giftMenu.getName(), Constants.NUMBER_OF_GIFT_EVENTS_APPLIED);
        }
        return new GiftMenuDto(giftMenu.getName(), Constants.NUMBER_OF_GIFT_EVENTS_NOT_APPLIED);
    }

    private boolean isGiftEventApplied() {
        return benefitDetails.keySet()
                .stream()
                .anyMatch(Event::isGiftEvent);
    }

    public Map<Event, Integer> getBenefitDetails() {
        return Collections.unmodifiableMap(benefitDetails);
    }
}
