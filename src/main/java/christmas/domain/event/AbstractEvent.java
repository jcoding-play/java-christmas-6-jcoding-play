package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

import java.util.List;
import java.util.Objects;

public abstract class AbstractEvent implements Event {
    private final List<Integer> applicableDates;

    public AbstractEvent() {
        this.applicableDates = initializeApplicableDates();
    }

    abstract List<Integer> initializeApplicableDates();

    @Override
    public boolean isApplicable(VisitDate visitDate, Order order) {
        return visitDate.isIncludedIn(applicableDates);
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
