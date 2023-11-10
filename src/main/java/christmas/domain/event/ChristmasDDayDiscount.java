package christmas.domain.event;

import java.util.List;
import java.util.stream.IntStream;

public class ChristmasDDayDiscount implements Event {
    private final List<Integer> applicableDates;

    public ChristmasDDayDiscount() {
        this.applicableDates = calculateApplicableDates();
    }

    private List<Integer> calculateApplicableDates() {
        return IntStream.rangeClosed(1, 25)
                .boxed()
                .toList();
    }
}
