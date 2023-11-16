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

class ChristmasDDayDiscountTest {
    private ChristmasDDayDiscount christmasDDayDiscount;

    @BeforeEach
    void setUp() {
        christmasDDayDiscount = new ChristmasDDayDiscount();
    }

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        assertThat(christmasDDayDiscount).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                        13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "25, true", "26, false", "31, false"})
    @DisplayName("크리스마스 디데이 할인은 25일까지 적용 가능하다.")
    void isApplicable(int date, boolean expected) {
        VisitDate visitDate = new VisitDate(12, date);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1)));

        boolean actual = christmasDDayDiscount.isApplicable(visitDate, orders);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("방문 날짜와 주문 메뉴를 통해 할인 금액을 계산할 수 있다.")
    void calculateDiscountedAmount() {
        VisitDate visitDate = new VisitDate(12, 3);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1)));

        int result = christmasDDayDiscount.calculateDiscountedAmount(visitDate, orders);

        assertThat(result).isEqualTo(1200);
    }
}