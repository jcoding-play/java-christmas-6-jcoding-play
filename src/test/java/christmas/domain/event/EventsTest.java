package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Main;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EventsTest {
    private Events events;

    @BeforeEach
    void setUp() {
        List<Event> eventList = List.of(new ChristmasDDayDiscount(),
                new WeekDayDiscount(), new WeekEndDiscount(),
                new SpecialDiscount(), new GiftEvent());
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
    void findApplicableEvents_V1() {
        Order order = new Order(
                List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Dessert.CHOCOLATE_CAKE, 1)));
        List<Event> result = events.findApplicableEvents(new VisitDate(12, 25), order);

        assertThat(result).containsExactly(
                new ChristmasDDayDiscount(), new WeekDayDiscount(), new SpecialDiscount());
    }

    @Test
    @DisplayName("총 주문 금액이 12만원 이상이면 증정 이벤트는 포함된다.")
    void findApplicableEvents_V2() {
        Order order = new Order(
                List.of(new OrderMenu(Main.T_BONE_STREAK, 3), new OrderMenu(Dessert.CHOCOLATE_CAKE, 1)));
        List<Event> result = events.findApplicableEvents(new VisitDate(12, 25), order);

        assertThat(result).containsExactly(
                new ChristmasDDayDiscount(), new WeekDayDiscount(), new SpecialDiscount(), new GiftEvent());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("방문하는 날짜가 금요일 또는 토요일이라면 주말 할인이 적용된다.")
    void findApplicableEvents_V3(int date) {
        Order order = new Order(
                List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Dessert.CHOCOLATE_CAKE, 1)));
        List<Event> result = events.findApplicableEvents(new VisitDate(12, date), order);

        assertThat(result).containsExactly(new ChristmasDDayDiscount(), new WeekEndDiscount());
    }

    @Test
    @DisplayName("26일 이후부터는 크리스마스 디데이 할인이 적용되지 않는다.")
    void findApplicableEvents_V4() {
        Order order = new Order(
                List.of(new OrderMenu(Main.T_BONE_STREAK, 3), new OrderMenu(Dessert.CHOCOLATE_CAKE, 1)));
        List<Event> result = events.findApplicableEvents(new VisitDate(12, 26), order);

        assertThat(result).containsExactly(new WeekDayDiscount(), new GiftEvent());
    }

    @Test
    @DisplayName("이벤트 달력에 별이 있으면 특별 할인이 적용된다.")
    void findApplicableEvents_V5() {
        Order order = new Order(
                List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Dessert.CHOCOLATE_CAKE, 1)));
        List<Event> result = events.findApplicableEvents(new VisitDate(12, 31), order);

        assertThat(result).containsExactly(new WeekDayDiscount(), new SpecialDiscount());
    }

    @Test
    @DisplayName("총 주문 금액이 10,000원보다 작다면 어떤 이벤트도 적용되지 않는다.")
    void findApplicableEvents_V6() {
        Order order = new Order(
                List.of(new OrderMenu(Dessert.ICE_CREAM, 1)));
        List<Event> result = events.findApplicableEvents(new VisitDate(12, 31), order);

        assertThat(result).isEmpty();
    }
}