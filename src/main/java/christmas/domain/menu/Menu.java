package christmas.domain.menu;

import christmas.domain.order.Count;

public interface Menu {

    default boolean isDrink() {
        return false;
    }

    boolean isMatchName(String name);

    int calculateOrderPrice(Count count);
}
