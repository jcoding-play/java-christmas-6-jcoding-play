package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class VisitDateTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    @DisplayName("12월 중 식당 예상 방문 날짜를 알 수 있다.")
    void createVisitDate(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate).isEqualTo(new VisitDate(date));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    @DisplayName("12월 중 식당 예상 방문 날짜가 1일보다 작거나 31일보다 크다면 예외가 발생한다.")
    void invalidVisitDate(int date) {
        assertThatThrownBy(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}