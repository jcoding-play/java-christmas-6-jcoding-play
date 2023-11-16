package christmas.domain.benefit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EventBadgeTest {

    @ParameterizedTest
    @CsvSource(value = {"4999, NOTHING", "5000, STAR", "9999, STAR", "10000, TREE", "19999, TREE", "20000, SANTA"})
    @DisplayName("혜택 금액을 통해 이벤트 배지를 찾을 수 있다.")
    void of(int totalBenefitAmount, EventBadge expected) {
        EventBadge result = EventBadge.of(totalBenefitAmount);

        assertThat(result).isEqualTo(expected);
    }
}