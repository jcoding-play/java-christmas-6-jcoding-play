package christmas.domain.event;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTest {

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        SpecialDiscount specialDiscount = new SpecialDiscount();

        assertThat(specialDiscount).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(3, 10, 17, 24, 25, 31);
    }
}