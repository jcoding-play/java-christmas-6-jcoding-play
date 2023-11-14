package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekEndDiscount extends AbstractEvent {
    private static final String EVENT_NAME = "주말 할인";

    private static final int FIRST_FRIDAY_DATE = 1;
    private static final int FIRST_SATURDAY_DATE = 2;
    private static final int LAST_DAY_OF_THE_EVENT = 31;
    private static final int WEEK_DATE = 7;
    private static final int NOTHING_COUNT = 0;
    private static final int DISCOUNTED_AMOUNT_PER_MAIN = 2023;

    @Override
    List<Integer> initializeApplicableDates() {
        List<Integer> applicableDates = new ArrayList<>();

        for (int firstDate = FIRST_FRIDAY_DATE; firstDate <= FIRST_SATURDAY_DATE; firstDate++) {
            for (int date = firstDate; date <= LAST_DAY_OF_THE_EVENT; date = date + WEEK_DATE) {
                applicableDates.add(date);
            }
        }
        Collections.sort(applicableDates);
        return applicableDates;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Orders orders) {
        return super.isApplicable(visitDate, orders) &&
                orders.countNumberOfMain() > NOTHING_COUNT;
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Orders orders) {
        int numberOfMain = orders.countNumberOfMain();
        return numberOfMain * DISCOUNTED_AMOUNT_PER_MAIN;
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }
}
