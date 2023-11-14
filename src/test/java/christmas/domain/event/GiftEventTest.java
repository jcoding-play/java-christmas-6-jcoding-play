package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GiftEventTest {
    private GiftEvent giftEvent;

    @BeforeEach
    void setUp() {
        giftEvent = new GiftEvent(new GiftMenu(Drink.CHAMPAGNE, 1));
    }

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        assertThat(giftEvent).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
    }

    @ParameterizedTest
    @MethodSource("ordersAndExpected")
    @DisplayName("증정 이벤트는 총 주문 금액이 12만원 이상일 경우 적용 가능하다.")
    void isApplicable(Orders orders, boolean expected) {
        VisitDate visitDate = new VisitDate(12, 3);

        boolean actual = giftEvent.isApplicable(visitDate, orders);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> ordersAndExpected() {
        return Stream.of(
                arguments(new Orders(List.of(new Order(Main.T_BONE_STREAK, 2), new Order(Dessert.ICE_CREAM, 2))), true),
                arguments(new Orders(List.of(new Order(Main.T_BONE_STREAK, 2), new Order(Drink.ZERO_COLA, 3))), false)
        );
    }

    @Test
    @DisplayName("방문 날짜와 주문 메뉴를 통해 할인 금액을 계산할 수 있다.")
    void calculateDiscountedAmount() {
        VisitDate visitDate = new VisitDate(12, 3);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 3)));

        int result = giftEvent.calculateDiscountedAmount(visitDate, orders);

        assertThat(result).isEqualTo(25000);
    }
}