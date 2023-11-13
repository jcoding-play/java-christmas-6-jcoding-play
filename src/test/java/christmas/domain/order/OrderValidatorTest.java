package christmas.domain.order;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderValidatorTest {
    private OrderValidator orderValidator;

    @BeforeEach
    void setUp() {
        orderValidator = new OrderValidator();
    }

    @Test
    @DisplayName("주문한 메뉴가 없는 경우 예외가 발생한다.")
    void validateEmptyOrder() {
        assertThatThrownBy(() -> orderValidator.validate(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("중복 메뉴가 존재하는 경우 예외가 발생한다.")
    void hasDuplicatedMenu() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Main.T_BONE_STREAK, 3));

        assertThatThrownBy(() -> orderValidator.validate(orderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료만 주문 시 예외가 발생한다.")
    void orderOnlyDrinks() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Drink.ZERO_COLA, 1), new OrderMenu(Drink.CHAMPAGNE, 3));

        assertThatThrownBy(() -> orderValidator.validate(orderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만 주문할 수 없습니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("메뉴의 총 개수가 20개를 넘을 시 예외가 발생한다.")
    void LargerThanMaximumOrderCount() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Drink.ZERO_COLA, 10), new OrderMenu(Main.T_BONE_STREAK, 11));

        assertThatThrownBy(() -> orderValidator.validate(orderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("메뉴는 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요. (현재 주문한 메뉴 개수 = 21)");
    }
}