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

    public int calculateEstimatedPaymentAmount(int totalOrderAmount) {
        int totalDiscountedAmount = calculateTotalDiscountedAmount();

        return totalOrderAmount - totalDiscountedAmount;
    }

    private int calculateTotalDiscountedAmount() {
        return benefitDetails.keySet()
                .stream()
                .filter(this::isNotGiftEvent)
                .map(benefitDetails::get)
                .reduce(Constants.INITIAL_AMOUNT, Integer::sum);
    }

    private boolean isNotGiftEvent(Event event) {
        return !event.isGiftEvent();
    }

    public GiftMenuDto findGiftMenu() {
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
