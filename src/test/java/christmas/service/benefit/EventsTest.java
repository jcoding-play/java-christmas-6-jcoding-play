package christmas.service.benefit;

import christmas.domain.VisitDate;
import christmas.domain.event.*;
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
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EventsTest {
    private Events events;

    @BeforeEach
    void setUp() {
        List<Event> eventList = List.of(new ChristmasDDayDiscount(),
                new WeekDayDiscount(), new WeekEndDiscount(),
                new SpecialDiscount(), new GiftEvent(new GiftMenu(Drink.CHAMPAGNE, 1)));
        events = new Events(eventList);
    }

    @Test
    @DisplayName("진행하는 이벤트 종류를 알 수 있다.")
    void findEvents() {
        assertThat(events).extracting("events", InstanceOfAssertFactories.list(Event.class))
                .hasSize(5);
    }


    @Test
    @DisplayName("총 주문 금액이 12만원 미만이면 증정 이벤트는 포함되지 않는다.")
    void checkApplicableBenefitDetails_V1() {
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1), new Order(Dessert.CHOCOLATE_CAKE, 1)));
        Map<Event, Integer> result = events.checkApplicableBenefitDetails(new VisitDate(12, 25), orders);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.keySet()).contains(new ChristmasDDayDiscount(), new WeekDayDiscount(), new SpecialDiscount());
    }

    @Test
    @DisplayName("총 주문 금액이 12만원 이상이면 증정 이벤트는 포함된다.")
    void checkApplicableBenefitDetails_V2() {
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 3), new Order(Dessert.CHOCOLATE_CAKE, 1)));
        Map<Event, Integer> result = events.checkApplicableBenefitDetails(new VisitDate(12, 25), orders);

        assertThat(result.size()).isEqualTo(4);
        assertThat(result.keySet()).contains(
                new ChristmasDDayDiscount(), new WeekDayDiscount(),
                new SpecialDiscount(), new GiftEvent(new GiftMenu(Drink.CHAMPAGNE, 1)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("방문하는 날짜가 금요일 또는 토요일이라면 주말 할인이 적용된다.")
    void checkApplicableBenefitDetails_V3(int date) {
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1), new Order(Dessert.CHOCOLATE_CAKE, 1)));
        Map<Event, Integer> result = events.checkApplicableBenefitDetails(new VisitDate(12, date), orders);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.keySet()).contains(new ChristmasDDayDiscount(), new WeekEndDiscount());
    }

    @Test
    @DisplayName("26일 이후부터는 크리스마스 디데이 할인이 적용되지 않는다.")
    void checkApplicableBenefitDetails_V4() {
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 3), new Order(Dessert.CHOCOLATE_CAKE, 1)));
        Map<Event, Integer> result = events.checkApplicableBenefitDetails(new VisitDate(12, 26), orders);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.keySet()).contains(new WeekDayDiscount(), new GiftEvent(new GiftMenu(Drink.CHAMPAGNE, 1)));
    }

    @Test
    @DisplayName("이벤트 달력에 별이 있으면 특별 할인이 적용된다.")
    void checkApplicableBenefitDetails_V5() {
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1), new Order(Dessert.CHOCOLATE_CAKE, 1)));
        Map<Event, Integer> result = events.checkApplicableBenefitDetails(new VisitDate(12, 31), orders);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.keySet()).contains(new WeekDayDiscount(), new SpecialDiscount());
    }

    @Test
    @DisplayName("총 주문 금액이 1만원보다 작다면 이벤트가 적용되지 않는다.")
    void checkApplicableBenefitDetails_V6() {
        Orders orders = new Orders(List.of(new Order(Dessert.ICE_CREAM, 1)));
        Map<Event, Integer> result = events.checkApplicableBenefitDetails(new VisitDate(12, 31), orders);

        assertThat(result).isEmpty();
    }
}