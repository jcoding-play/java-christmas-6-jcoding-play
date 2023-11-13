package christmas.domain.order;

import christmas.domain.menu.Menu;

import java.util.Collections;
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

    public int countNumberOfDessert() {
        return (int) orderMenus.stream()
                .map(OrderMenu::getMenu)
                .filter(Menu::isDessert)
                .count();
    }

    public int countNumberOfMain() {
        return (int) orderMenus.stream()
                .map(OrderMenu::getMenu)
                .filter(Menu::isMain)
                .count();
    }

    public List<OrderMenu> getOrderMenus() {
        return Collections.unmodifiableList(orderMenus);
    }
}
