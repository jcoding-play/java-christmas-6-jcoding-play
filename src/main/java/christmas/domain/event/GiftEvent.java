package christmas.domain.event;

import java.util.List;
import java.util.stream.IntStream;

public class GiftEvent implements Event {
    private final List<Integer> applicableDates;

    public GiftEvent() {
        this.applicableDates = calculateApplicableDates();
    }

    private List<Integer> calculateApplicableDates() {
        return IntStream.rangeClosed(1, 31)
                .boxed()
                .toList();
    }
}
