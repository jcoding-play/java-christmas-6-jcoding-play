package christmas.domain.order;

import java.util.List;

public class Order {
    private static final int INITIAL_PRICE = 0;

    private final List<OrderMenu> orderMenus;

    public Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public int calculateTotalOrderPrice() {
        return orderMenus.stream()
                .map(OrderMenu::calculateOrderPrice)
                .reduce(INITIAL_PRICE, Integer::sum);
    }

    public void validate(OrderValidator orderValidator) {
        orderValidator.validate(orderMenus);
    }
}
