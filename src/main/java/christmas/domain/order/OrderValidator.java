package christmas.domain.order;

import christmas.utils.Constants;

import java.util.List;

public class OrderValidator {
    protected static final String INVALID_ORDER_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_ONLY_DRINKS_EXCEPTION_MESSAGE = "음료만 주문할 수 없습니다. 다시 입력해 주세요.";
    private static final String LARGER_THAN_MAXIMUM_ORDER_COUNT_EXCEPTION_FORMAT =
            "메뉴는 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요. (현재 주문한 메뉴 개수 = %d)";

    private static final int MAXIMUM_ORDER_COUNT = 20;

    public void validate(List<Order> orders) {
        validateEmptyOrder(orders);
        validateDuplicatedMenu(orders);
        validateOrderOnlyDrinks(orders);
        validateTotalOrderCount(orders);
    }

    private void validateEmptyOrder(List<Order> orders) {
        if (orders.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private void validateDuplicatedMenu(List<Order> orders) {
        if (hasDuplicatedMenu(orders)) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private boolean hasDuplicatedMenu(List<Order> orders) {
        return orders.stream()
                .map(Order::getMenu)
                .distinct()
                .count() != orders.size();
    }

    private void validateOrderOnlyDrinks(List<Order> orders) {
        if (hasOrderedOnlyDrinks(orders)) {
            throw new IllegalArgumentException(ORDER_ONLY_DRINKS_EXCEPTION_MESSAGE);
        }
    }

    private boolean hasOrderedOnlyDrinks(List<Order> orders) {
        return orders.stream()
                .allMatch(Order::isDrink);
    }

    private void validateTotalOrderCount(List<Order> orders) {
        int totalOrderCount = calculateTotalOrderCount(orders);

        if (isLargerThanMaximumOrderCount(totalOrderCount)) {
            throw new IllegalArgumentException(
                    String.format(LARGER_THAN_MAXIMUM_ORDER_COUNT_EXCEPTION_FORMAT, totalOrderCount));
        }
    }

    private int calculateTotalOrderCount(List<Order> orders) {
        return orders.stream()
                .map(Order::getCount)
                .reduce(Constants.INITIAL_COUNT, Integer::sum);
    }

    private boolean isLargerThanMaximumOrderCount(int totalOrderCount) {
        return totalOrderCount > MAXIMUM_ORDER_COUNT;
    }
}
