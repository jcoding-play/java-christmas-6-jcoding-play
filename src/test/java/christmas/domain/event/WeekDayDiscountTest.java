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

class WeekDayDiscountTest {
    private WeekDayDiscount weekDayDiscount;

    @BeforeEach
    void setUp() {
        weekDayDiscount = new WeekDayDiscount();
    }

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        assertThat(weekDayDiscount).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(3, 4, 5, 6, 7, 10, 11, 12, 13, 14,
                        17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, false", "2, false", "3, true", "4, true"})
    @DisplayName("평일(일요일~목요일)에만 할인이 가능하다.")
    void isApplicable_V1(int date, boolean expected) {
        VisitDate visitDate = new VisitDate(12, date);
        Orders orders = new Orders(List.of(new Order(Dessert.CHOCOLATE_CAKE, 2)));

        boolean actual = weekDayDiscount.isApplicable(visitDate, orders);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"3, false", "4, false"})
    @DisplayName("평일(일요일~목요일)이라도 디저트 메뉴를 시키지 않았으면 할인 적용이 안된다.")
    void isApplicable_V2(int date, boolean expected) {
        VisitDate visitDate = new VisitDate(12, date);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 2)));

        boolean actual = weekDayDiscount.isApplicable(visitDate, orders);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("방문 날짜와 주문 메뉴를 통해 할인 금액을 계산할 수 있다.")
    void calculateDiscountedAmount() {
        VisitDate visitDate = new VisitDate(12, 3);
        Orders orders = new Orders(List.of(new Order(Dessert.CHOCOLATE_CAKE, 2)));

        int result = weekDayDiscount.calculateDiscountedAmount(visitDate, orders);

        assertThat(result).isEqualTo(4046);
    }
}