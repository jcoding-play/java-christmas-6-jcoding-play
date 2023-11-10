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
    @DisplayName("중복 메뉴가 존재하는 경우 예외가 발생한다.")
    void hasDuplicatedMenu() {
        Order order = new Order(List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Main.T_BONE_STREAK, 3)));

        assertThatThrownBy(() -> order.validate(new OrderValidator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료만 주문 시 예외가 발생한다.")
    void orderOnlyDrinks() {
        Order order = new Order(List.of(new OrderMenu(Drink.ZERO_COLA, 1), new OrderMenu(Drink.CHAMPAGNE, 3)));

        assertThatThrownBy(() -> order.validate(new OrderValidator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만 주문할 수 없습니다. 다시 입력해 주세요.");
    }
    
    @Test
    @DisplayName("메뉴의 총 개수가 20개를 넘을 시 예외가 발생한다.")
    void LargerThanMaximumOrderCount() {
        Order order = new Order(List.of(new OrderMenu(Drink.ZERO_COLA, 10), new OrderMenu(Main.T_BONE_STREAK, 11)));

        assertThatThrownBy(() -> order.validate(new OrderValidator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("메뉴는 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요. (현재 주문한 메뉴 개수 = 21)");
    }

    @Test
    @DisplayName("할인 전 총주문 금액을 계산할 수 있다.")
    void calculateTotalOrderPrice() {
        Order order = new Order(List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Drink.ZERO_COLA, 3)));

        int result = order.calculateTotalOrderPrice();

        assertThat(result).isEqualTo(64000);
    }
}