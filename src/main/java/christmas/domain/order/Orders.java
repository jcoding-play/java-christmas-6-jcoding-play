package christmas.domain.order;

import christmas.utils.Constants;

import java.util.Collections;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public void validate(OrderValidator orderValidator) {
        orderValidator.validate(orders);
    }

    public int calculateTotalOrderAmount() {
        return orders.stream()
                .map(Order::calculateOrderAmount)
                .reduce(Constants.INITIAL_AMOUNT, Integer::sum);
    }

    public int countNumberOfDessert() {
        return orders.stream()
                .filter(Order::isDessert)
                .map(Order::getCount)
                .reduce(Constants.INITIAL_COUNT, Integer::sum);
    }

    public int countNumberOfMain() {
        return orders.stream()
                .filter(Order::isMain)
                .map(Order::getCount)
                .reduce(Constants.INITIAL_COUNT, Integer::sum);
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
