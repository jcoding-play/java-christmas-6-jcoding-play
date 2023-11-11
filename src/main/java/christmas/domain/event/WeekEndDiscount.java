package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekEndDiscount extends AbstractEvent {

    @Override
    List<Integer> calculateApplicableDates() {
        List<Integer> applicableDates = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            for (int j = i; j <= 31; j = j + 7) {
                applicableDates.add(j);
            }
        }
        Collections.sort(applicableDates);
        return applicableDates;
    }

    @Override
    public boolean isApplicable(VisitDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) &&
                order.countNumberOfMain() > 0;
    }

    @Override
    public int calculateDiscountedAmount(VisitDate visitDate, Order order) {
        int numberOfMain = order.countNumberOfMain();
        return numberOfMain * 2023;
    }
}
