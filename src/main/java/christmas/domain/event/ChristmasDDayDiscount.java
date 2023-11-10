package christmas.domain.event;

import java.util.List;
import java.util.stream.IntStream;

public class ChristmasDDayDiscount extends AbstractEvent {

    @Override
    List<Integer> calculateApplicableDates() {
        return IntStream.rangeClosed(1, 25)
                .boxed()
                .toList();
    }
}
