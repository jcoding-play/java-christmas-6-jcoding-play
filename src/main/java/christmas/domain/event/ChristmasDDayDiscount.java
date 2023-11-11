package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.List;
import java.util.stream.IntStream;

public class ChristmasDDayDiscount extends AbstractEvent {

    @Override
    List<Integer> calculateApplicableDates() {
        return IntStream.rangeClosed(1, 25)
                .boxed()
                .toList();
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        int startingDiscountAmount = 1000;

        int difference = visitDate.calculateDifferenceInDate(1);
        int additionalDiscountAmount = difference * 100;

        return startingDiscountAmount + additionalDiscountAmount;
    }
}
