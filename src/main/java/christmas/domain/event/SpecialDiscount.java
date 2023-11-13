package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.List;

public class SpecialDiscount extends AbstractEvent {
    private static final String EVENT_NAME = "특별 할인";

    private static final int FIRST_DAY_OF_THE_EVENT = 3;
    private static final int SECOND_DAY_OF_THE_EVENT = 10;
    private static final int THIRD_DAY_OF_THE_EVENT = 17;
    private static final int FOURTH_DAY_OF_THE_EVENT = 24;
    private static final int FIFTH_DAY_OF_THE_EVENT = 25;
    private static final int SIXTH_DAY_OF_THE_EVENT = 31;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    @Override
    List<Integer> initializeApplicableDates() {
        return List.of(
                FIRST_DAY_OF_THE_EVENT, SECOND_DAY_OF_THE_EVENT, THIRD_DAY_OF_THE_EVENT,
                FOURTH_DAY_OF_THE_EVENT, FIFTH_DAY_OF_THE_EVENT, SIXTH_DAY_OF_THE_EVENT);
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        return SPECIAL_DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }
}
