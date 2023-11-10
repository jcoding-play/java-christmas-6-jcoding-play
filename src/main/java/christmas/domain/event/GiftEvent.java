package christmas.domain.event;

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
    public boolean isApplicable(int date, int totalOrderPrice) {
        return super.isApplicable(date, totalOrderPrice) && totalOrderPrice >= MINIMUM_APPLICABLE_ORDER_PRICE;
    }
}
