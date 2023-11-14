package christmas.domain.order;

import christmas.utils.Constants;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders1 = (Orders) o;
        return Objects.equals(orders, orders1.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }
}
