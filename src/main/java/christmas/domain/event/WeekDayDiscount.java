package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekDayDiscount extends AbstractEvent {

    @Override
    List<Integer> calculateApplicableDates() {
        List<Integer> applicableDates = new ArrayList<>();

        for (int i = 3; i <= 7; i++) {
            for (int j = i; j <= 31; j = j + 7) {
                applicableDates.add(j);
            }
        }
        Collections.sort(applicableDates);
        return applicableDates;
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        int numberOfDessert = order.countNumberOfDessert();
        return numberOfDessert * 2023;
    }
}
