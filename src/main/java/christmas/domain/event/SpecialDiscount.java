package christmas.domain.event;

import java.util.List;

public class SpecialDiscount implements Event {
    private final List<Integer> applicableDates;

    public SpecialDiscount() {
        this.applicableDates = calculateApplicableDates();
    }

    private List<Integer> calculateApplicableDates() {
        return List.of(3, 10, 17, 24, 25, 31);
    }
}
