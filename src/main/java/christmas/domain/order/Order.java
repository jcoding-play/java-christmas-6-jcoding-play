package christmas.domain.order;

import christmas.utils.Constants;

import java.util.Collections;
import java.util.List;

public class Order {
    private final List<OrderMenu> orderMenus;

    public Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public void validate(OrderValidator orderValidator) {
        orderValidator.validate(orderMenus);
    }

    public int calculateTotalOrderAmount() {
        return orderMenus.stream()
                .map(OrderMenu::calculateOrderAmount)
                .reduce(Constants.INITIAL_AMOUNT, Integer::sum);
    }

    public int countNumberOfDessert() {
        return orderMenus.stream()
                .filter(OrderMenu::isDessert)
                .map(OrderMenu::getCount)
                .reduce(Constants.INITIAL_COUNT, Integer::sum);
    }

    public int countNumberOfMain() {
        return orderMenus.stream()
                .filter(OrderMenu::isMain)
                .map(OrderMenu::getCount)
                .reduce(Constants.INITIAL_COUNT, Integer::sum);
    }

    public List<OrderMenu> getOrderMenus() {
        return Collections.unmodifiableList(orderMenus);
    }
}
