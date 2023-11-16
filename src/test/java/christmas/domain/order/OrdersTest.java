package christmas.domain.order;

import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrdersTest {

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

    @Test
    @DisplayName("디저트 개수를 셀 수 있다.")
    void countNumberOfDessert() {
        Orders orders = new Orders(List.of(new Order(Dessert.CHOCOLATE_CAKE, 2), new Order(Dessert.ICE_CREAM, 3)));

        int result = orders.countNumberOfDessert();

        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("메인 메뉴 개수를 셀 수 있다.")
    void countNumberOfMain() {
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 5), new Order(Main.BBQ_RIBS, 1)));

        int result = orders.countNumberOfMain();

        assertThat(result).isEqualTo(6);
    }
}