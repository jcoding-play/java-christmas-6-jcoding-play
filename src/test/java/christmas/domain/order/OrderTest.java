package christmas.domain.order;

import christmas.domain.menu.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Test
    @DisplayName("주문할 메뉴와 개수를 알 수 있다.")
    void createOrder() {
        Order order = new Order(Main.T_BONE_STREAK, 1);
        assertThat(order).isEqualTo(new Order(Main.T_BONE_STREAK, 1));
    }

    @Test
    @DisplayName("주문한 메뉴의 개수가 1보다 작을 경우 예외가 발생한다.")
    void lessThanMinimumOrderCount() {
        assertThatThrownBy(() -> new Order(Main.T_BONE_STREAK, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}