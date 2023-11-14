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
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1), new Order(Drink.ZERO_COLA, 1)));

        assertThat(orders).extracting("orders", InstanceOfAssertFactories.list(Order.class))
                .containsExactly(new Order(Main.T_BONE_STREAK, 1), new Order(Drink.ZERO_COLA, 1));
    }

    @Test
    @DisplayName("할인 전 총주문 금액을 계산할 수 있다.")
    void calculateTotalOrderPrice() {
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1), new Order(Drink.ZERO_COLA, 3)));

        int result = orders.calculateTotalOrderAmount();

        assertThat(result).isEqualTo(64000);
    }
}