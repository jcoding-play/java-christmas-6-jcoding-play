package christmas.domain.order;

import christmas.domain.menu.Menu;

import java.util.Objects;

public class OrderMenu {
    private final Menu menu;
    private final Count count;

    public OrderMenu(Menu menu, int count) {
        validateMenu(menu);
        this.menu = menu;
        this.count = new Count(count);
    }

    private void validateMenu(Menu menu) {
        if (isNotOnTheMenu(menu)) {
            throw new IllegalArgumentException(OrderValidator.INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private boolean isNotOnTheMenu(Menu menu) {
        return menu == null;
    }

    public boolean isDrink() {
        return menu.isDrink();
    }

    public boolean isDessert() {
        return menu.isDessert();
    }

    public boolean isMain() {
        return menu.isMain();
    }

    public int calculateOrderAmount() {
        return count.multiplyPrice(menu.getPrice());
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMenu orderMenu = (OrderMenu) o;
        return Objects.equals(menu, orderMenu.menu) && Objects.equals(count, orderMenu.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, count);
    }
}
