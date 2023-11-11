package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

public interface Event {

    boolean isApplicable(VisitDate visitDate, int totalOrderPrice);

    int calculateDiscountedAmount(VisitDate visitDate, Order order);
}
