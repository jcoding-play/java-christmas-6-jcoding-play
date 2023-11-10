package christmas.domain.event;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeekDayDiscountTest {

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount();

        assertThat(weekDayDiscount).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(3, 4, 5, 6, 7, 10, 11, 12, 13, 14,
                        17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31);
    }
}