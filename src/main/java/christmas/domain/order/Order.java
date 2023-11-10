package christmas.domain.order;

import java.util.List;

public class Order {
    private static final int MAXIMUM_ORDER_COUNT = 20;
    private static final int INITIAL_COUNT = 0;

    private final List<OrderMenu> orderMenus;

    public Order(List<OrderMenu> orderMenus) {
        validateOrderMenus(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validateOrderMenus(List<OrderMenu> orderMenus) {
        if (hasDuplicatedMenu(orderMenus)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (hasOrderedOnlyDrinks(orderMenus)) {
            throw new IllegalArgumentException("음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        }

        int totalOrderCount = calculateTotalOrderCount(orderMenus);
        if (isLargerThanMaximumOrderCount(totalOrderCount)) {
            throw new IllegalArgumentException("메뉴는 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요. (현재 주문한 메뉴 개수 = " + totalOrderCount + ")");
        }
    }

    private boolean hasDuplicatedMenu(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenu)
                .distinct()
                .count() != orderMenus.size();
    }

    private boolean hasOrderedOnlyDrinks(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .allMatch(OrderMenu::isDrink);
    }

    private int calculateTotalOrderCount(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getCount)
                .reduce(INITIAL_COUNT, Integer::sum);
    }

    private boolean isLargerThanMaximumOrderCount(int totalOrderCount) {
        return totalOrderCount > MAXIMUM_ORDER_COUNT;
    }
}
