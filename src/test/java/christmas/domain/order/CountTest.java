package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("개수만큼 가격을 곱할 수 있다.")
    void multiplyPrice() {
        Count count = new Count(3);
        int result = count.multiplyPrice(9000);

        assertThat(result).isEqualTo(27000);
    }
}