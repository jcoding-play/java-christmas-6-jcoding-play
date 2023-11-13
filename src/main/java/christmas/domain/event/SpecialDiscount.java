package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.List;

public class SpecialDiscount extends AbstractEvent {

    @Override
    List<Integer> calculateApplicableDates() {
        return List.of(3, 10, 17, 24, 25, 31);
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        return 1000;
    }

    @Override
    public String getName() {
        return "특별 할인";
    }
}
