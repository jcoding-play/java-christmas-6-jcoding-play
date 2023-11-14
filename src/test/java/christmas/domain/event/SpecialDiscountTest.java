package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.menu.Main;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTest {
    private SpecialDiscount specialDiscount;

    @BeforeEach
    void setUp() {
        specialDiscount = new SpecialDiscount();
    }

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        assertThat(specialDiscount).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(3, 10, 17, 24, 25, 31);
    }

    @ParameterizedTest
    @CsvSource(value = {"3, true", "10, true", "31, true", "2, false", "30, false", "26, false"})
    @DisplayName("특별 할인은 달력에 별이 있는 날짜만 할인 가능하다.")
    void isApplicable(int date, boolean expected) {
        VisitDate visitDate = new VisitDate(12, date);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1)));

        boolean actual = specialDiscount.isApplicable(visitDate, orders);

        assertThat(actual).isEqualTo(expected);
    }
}