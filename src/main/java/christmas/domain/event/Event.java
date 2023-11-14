package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

public interface Event {

    default boolean isGiftEvent() {
        return false;
    }

    boolean isApplicable(VisitDate visitDate, Orders order);

    int calculateDiscountedAmount(VisitDate visitDate, Orders order);

    String getName();
}
