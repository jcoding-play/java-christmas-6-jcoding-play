package christmas.domain.event;

import java.util.List;

public class SpecialDiscount extends AbstractEvent {

    @Override
    List<Integer> calculateApplicableDates() {
        return List.of(3, 10, 17, 24, 25, 31);
    }
}
