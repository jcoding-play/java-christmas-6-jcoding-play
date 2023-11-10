package christmas.domain.order;

import christmas.domain.menu.Menu;

import java.util.Objects;

public class OrderMenu {
    private final Menu menu;
    private final Count count;

    public OrderMenu(Menu menu, int count) {
        this.menu = menu;
        this.count = new Count(count);
    }

    public boolean isDrink() {
        return menu.isDrink();
    }

    public int calculateOrderPrice() {
        return menu.getPrice() * count.getCount();
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
