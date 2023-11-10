package christmas.domain.order;

import christmas.domain.menu.Menu;

import java.util.Objects;

public class OrderMenu {
    private static final int MINIMUM_ORDER_COUNT = 1;

    private final Menu menu;
    private final int count;

    public OrderMenu(Menu menu, int count) {
        validateCount(count);
        this.menu = menu;
        this.count = count;
    }

    private void validateCount(int count) {
        if (isLessThanMinimumOrderCount(count)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isLessThanMinimumOrderCount(int count) {
        return count < MINIMUM_ORDER_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMenu orderMenu = (OrderMenu) o;
        return count == orderMenu.count && Objects.equals(menu, orderMenu.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, count);
    }
}