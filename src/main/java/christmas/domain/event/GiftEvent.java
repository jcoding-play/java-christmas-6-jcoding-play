package christmas.domain.event;

import java.util.List;
import java.util.stream.IntStream;

public class GiftEvent extends AbstractEvent {

    @Override
    List<Integer> calculateApplicableDates() {
        return IntStream.rangeClosed(1, 31)
                .boxed()
                .toList();
    }
}
