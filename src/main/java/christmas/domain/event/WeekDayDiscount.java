package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekDayDiscount extends AbstractEvent {
    private static final String EVENT_NAME = "평일 할인";

    private static final int FIRST_SUNDAY_DATE = 3;
    private static final int FIRST_THURSDAY_DATE = 7;
    private static final int LAST_DAY_OF_THE_EVENT = 31;
    private static final int WEEK_DATE = 7;
    private static final int NOTHING_COUNT = 0;
    private static final int DISCOUNTED_AMOUNT_PER_DESSERT = 2023;

    @Override
    List<Integer> initializeApplicableDates() {
        List<Integer> applicableDates = new ArrayList<>();

        for (int firstDate = FIRST_SUNDAY_DATE; firstDate <= FIRST_THURSDAY_DATE; firstDate++) {
            for (int date = firstDate; date <= LAST_DAY_OF_THE_EVENT; date = date + WEEK_DATE) {
                applicableDates.add(date);
            }
        }
        Collections.sort(applicableDates);
        return applicableDates;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) &&
                order.countNumberOfDessert() > NOTHING_COUNT;
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        int numberOfDessert = order.countNumberOfDessert();
        return numberOfDessert * DISCOUNTED_AMOUNT_PER_DESSERT;
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }
}
