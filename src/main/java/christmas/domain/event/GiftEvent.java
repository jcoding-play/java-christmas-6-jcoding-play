package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.List;
import java.util.stream.IntStream;

public class GiftEvent extends AbstractEvent {
    private static final int MINIMUM_APPLICABLE_ORDER_PRICE = 120000;

    @Override
    List<Integer> calculateApplicableDates() {
        return IntStream.rangeClosed(1, 31)
                .boxed()
                .toList();
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) &&
                order.calculateTotalOrderPrice() >= MINIMUM_APPLICABLE_ORDER_PRICE;
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        return 25000;
    }
}
