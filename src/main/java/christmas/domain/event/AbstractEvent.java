package christmas.domain.event;

import java.util.List;

public abstract class AbstractEvent implements Event {
    private final List<Integer> applicableDates;

    public AbstractEvent() {
        this.applicableDates = calculateApplicableDates();
    }

    abstract List<Integer> calculateApplicableDates();
}
