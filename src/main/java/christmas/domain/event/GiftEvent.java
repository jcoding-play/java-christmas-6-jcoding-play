package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

import java.util.List;
import java.util.stream.IntStream;

public class GiftEvent extends AbstractEvent {
    private static final String EVENT_NAME = "증정 이벤트";

    private static final int FIRST_DAY_OF_THE_EVENT = 1;
    private static final int LAST_DAY_OF_THE_EVENT = 31;
    private static final int MINIMUM_APPLICABLE_ORDER_PRICE = 120000;

    private final GiftMenu giftMenu;

    public GiftEvent(GiftMenu giftMenu) {
        this.giftMenu = giftMenu;
    }

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
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        return super.isApplicable(visitDate, orders) &&
                orders.calculateTotalOrderAmount() >= MINIMUM_APPLICABLE_ORDER_PRICE;
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Orders orders) {
        return giftMenu.calculateTotalPrice();
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }

    public GiftMenu getGiftMenu() {
        return giftMenu;
    }
}
