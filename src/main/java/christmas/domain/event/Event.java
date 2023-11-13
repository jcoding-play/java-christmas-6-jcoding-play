package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

public interface Event {

    default boolean isGiftEvent() {
        return false;
    }

    boolean isApplicable(VisitDate visitDate, Order order);

    int calculateDiscountedAmount(VisitDate visitDate, Order order);

    String getName();
}
