package christmas.domain.order;

import christmas.utils.Constants;

import java.util.Objects;

public class Count {
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
        return count < Constants.MINIMUM_COUNT;
    }

    public int multiplyPrice(int price) {
        return price * count;
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
