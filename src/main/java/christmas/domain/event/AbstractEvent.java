package christmas.domain.event;

import java.util.List;
import java.util.Objects;

public abstract class AbstractEvent implements Event {
    private final List<Integer> applicableDates;

    public AbstractEvent() {
        this.applicableDates = calculateApplicableDates();
    }

    abstract List<Integer> calculateApplicableDates();

    @Override
    public boolean isApplicable(int date, int totalOrderPrice) {
        return applicableDates.contains(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEvent that = (AbstractEvent) o;
        return Objects.equals(applicableDates, that.applicableDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicableDates);
    }
}
