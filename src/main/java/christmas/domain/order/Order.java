package christmas.domain.order;

import java.util.Collections;
import java.util.List;

public class Order {
    private static final int INITIAL_PRICE = 0;
    private static final int INITIAL_COUNT = 0;

    private final List<OrderMenu> orderMenus;

    public Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public int calculateTotalOrderAmount() {
        return orderMenus.stream()
                .map(OrderMenu::calculateOrderAmount)
                .reduce(INITIAL_PRICE, Integer::sum);
    }

    public void validate(OrderValidator orderValidator) {
        orderValidator.validate(orderMenus);
    }

    public int countNumberOfDessert() {
        return orderMenus.stream()
                .filter(OrderMenu::isDessert)
                .map(OrderMenu::getCount)
                .reduce(INITIAL_COUNT, Integer::sum);
    }

    public int countNumberOfMain() {
        return orderMenus.stream()
                .filter(OrderMenu::isMain)
                .map(OrderMenu::getCount)
                .reduce(INITIAL_COUNT, Integer::sum);
    }

    public List<OrderMenu> getOrderMenus() {
        return Collections.unmodifiableList(orderMenus);
    }
}
