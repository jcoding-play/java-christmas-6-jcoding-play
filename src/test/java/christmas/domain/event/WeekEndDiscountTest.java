package christmas.domain.event;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeekEndDiscountTest {

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        WeekEndDiscount weekEndDiscount = new WeekEndDiscount();

        assertThat(weekEndDiscount).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    }
}