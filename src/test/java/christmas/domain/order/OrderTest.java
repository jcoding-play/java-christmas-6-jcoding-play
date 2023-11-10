package christmas.domain.order;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("전체 주문 내역을 알 수 있다.")
    void createOrder() {
        Order order = new Order(List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Drink.ZERO_COLA, 1)));

        assertThat(order).extracting("orderMenus", InstanceOfAssertFactories.list(OrderMenu.class))
                .containsExactly(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Drink.ZERO_COLA, 1));
    }

    @Test
    @DisplayName("할인 전 총주문 금액을 계산할 수 있다.")
    void calculateTotalOrderPrice() {
        Order order = new Order(List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Drink.ZERO_COLA, 3)));

        int result = order.calculateTotalOrderPrice();

        assertThat(result).isEqualTo(64000);
    }
}