package christmas.domain.order;

import java.util.List;

public class OrderValidator {
    protected static final String INVALID_ORDER_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_ONLY_DRINKS_EXCEPTION_MESSAGE = "음료만 주문할 수 없습니다. 다시 입력해 주세요.";
    private static final String LARGER_THAN_MAXIMUM_ORDER_COUNT_EXCEPTION_FORMAT =
            "메뉴는 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요. (현재 주문한 메뉴 개수 = %d)";

    private static final int MAXIMUM_ORDER_COUNT = 20;
    private static final int INITIAL_COUNT = 0;

    public void validate(List<OrderMenu> orderMenus) {
        validateDuplicatedMenu(orderMenus);
        validateOrderOnlyDrinks(orderMenus);
        validateTotalOrderCount(orderMenus);
    }

    private void validateDuplicatedMenu(List<OrderMenu> orderMenus) {
        if (hasDuplicatedMenu(orderMenus)) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private boolean hasDuplicatedMenu(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenu)
                .distinct()
                .count() != orderMenus.size();
    }

    private void validateOrderOnlyDrinks(List<OrderMenu> orderMenus) {
        if (hasOrderedOnlyDrinks(orderMenus)) {
            throw new IllegalArgumentException(ORDER_ONLY_DRINKS_EXCEPTION_MESSAGE);
        }
    }

    private boolean hasOrderedOnlyDrinks(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .allMatch(OrderMenu::isDrink);
    }

    private void validateTotalOrderCount(List<OrderMenu> orderMenus) {
        int totalOrderCount = calculateTotalOrderCount(orderMenus);

        if (isLargerThanMaximumOrderCount(totalOrderCount)) {
            throw new IllegalArgumentException(
                    String.format(LARGER_THAN_MAXIMUM_ORDER_COUNT_EXCEPTION_FORMAT, totalOrderCount));
        }
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
