package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.menu.Dessert;
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

class WeekEndDiscountTest {
    private WeekEndDiscount weekEndDiscount;

    @BeforeEach
    void setUp() {
        weekEndDiscount = new WeekEndDiscount();
    }

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        assertThat(weekEndDiscount).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, true", "3, false", "4, false"})
    @DisplayName("주말(금요일~토요일)에만 할인이 가능하다.")
    void isApplicable_V1(int date, boolean expected) {
        VisitDate visitDate = new VisitDate(12, date);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 2)));

        boolean actual = weekEndDiscount.isApplicable(visitDate, orders);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, false", "2, false"})
    @DisplayName("주말(금요일~토요일)이라도 메인 메뉴를 시키지 않았으면 할인 적용이 안된다.")
    void isApplicable_V2(int date, boolean expected) {
        VisitDate visitDate = new VisitDate(12, date);
        Orders orders = new Orders(List.of(new Order(Dessert.CHOCOLATE_CAKE, 2)));

        boolean actual = weekEndDiscount.isApplicable(visitDate, orders);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("방문 날짜와 주문 메뉴를 통해 할인 금액을 계산할 수 있다.")
    void calculateDiscountedAmount() {
        VisitDate visitDate = new VisitDate(12, 1);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 2)));

        int result = weekEndDiscount.calculateDiscountedAmount(visitDate, orders);

        assertThat(result).isEqualTo(4046);
    }
}