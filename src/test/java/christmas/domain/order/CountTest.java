package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CountTest {

    @Test
    @DisplayName("주문할 메뉴의 개수를 알 수 있다.")
    void createCount() {
        Count count = new Count(1);
        assertThat(count).isEqualTo(new Count(1));
    }

    @Test
    @DisplayName("주문할 메뉴의 개수가 1보다 작으면 예외가 발생한다.")
    void invalidCount() {
        assertThatThrownBy(() -> new Count(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}