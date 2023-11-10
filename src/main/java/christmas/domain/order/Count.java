package christmas.domain.order;

import java.util.Objects;

public class Count {
    private static final int MINIMUM_ORDER_COUNT = 1;

    private final int count;

    public Count(int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(int count) {
        if (isLessThanMinimumOrderCount(count)) {
            throw new IllegalArgumentException(OrderValidator.INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private boolean isLessThanMinimumOrderCount(int count) {
        return count < MINIMUM_ORDER_COUNT;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count1 = (Count) o;
        return count == count1.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
