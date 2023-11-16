package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

import java.util.List;
import java.util.stream.IntStream;

public class ChristmasDDayDiscount extends AbstractEvent {
    private static final String EVENT_NAME = "크리스마스 디데이 할인";

    private static final int FIRST_DAY_OF_THE_EVENT = 1;
    private static final int LAST_DAY_OF_THE_EVENT = 25;
    private static final int STARTING_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNTED_AMOUNT_ADDED_PER_DAY = 100;

    @Override
    List<Integer> initializeApplicableDates() {
        return IntStream.rangeClosed(FIRST_DAY_OF_THE_EVENT, LAST_DAY_OF_THE_EVENT)
                .boxed()
                .toList();
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Orders orders) {
        int difference = visitDate.calculateDifferenceInDate(FIRST_DAY_OF_THE_EVENT);
        int additionalDiscountAmount = difference * DISCOUNTED_AMOUNT_ADDED_PER_DAY;

        return STARTING_DISCOUNT_AMOUNT + additionalDiscountAmount;
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }
}
