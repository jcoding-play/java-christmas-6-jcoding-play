package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.List;
import java.util.stream.IntStream;

public class GiftEvent extends AbstractEvent {
    private static final String EVENT_NAME = "증정 이벤트";

    private static final int FIRST_DAY_OF_THE_EVENT = 1;
    private static final int LAST_DAY_OF_THE_EVENT = 31;
    private static final int MINIMUM_APPLICABLE_ORDER_PRICE = 120000;
    private static final int PRICE_OF_GIFT_MENU = 25000;

    @Override
    List<Integer> initializeApplicableDates() {
        return IntStream.rangeClosed(FIRST_DAY_OF_THE_EVENT, LAST_DAY_OF_THE_EVENT)
                .boxed()
                .toList();
    }

    @Override
    public boolean isGiftEvent() {
        return true;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) &&
                order.calculateTotalOrderAmount() >= MINIMUM_APPLICABLE_ORDER_PRICE;
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        return PRICE_OF_GIFT_MENU;
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }
}
