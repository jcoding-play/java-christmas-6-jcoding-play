package christmas.service.benefit;

import christmas.domain.benefit.EventBenefits;
import christmas.domain.VisitDate;
import christmas.domain.benefit.EventBadge;
import christmas.domain.event.*;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.order.Orders;
import christmas.domain.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BenefitServiceTest {
    private BenefitService benefitService;

    @BeforeEach
    void setUp() {
        List<Event> events = List.of(new ChristmasDDayDiscount(),
                new WeekDayDiscount(), new WeekEndDiscount(),
                new SpecialDiscount(), new GiftEvent());
        benefitService = new BenefitService(new Events(events));
    }
    
    @Test
    @DisplayName("고객에게 적용 가능한 총혜택 내역을 확인할 수 있다.")
    void checkApplicableBenefitDetails() {
        VisitDate visitDate = new VisitDate(12, 25);
        Orders orders = new Orders(List.of(new Order(Main.T_BONE_STREAK, 1), new Order(Drink.ZERO_COLA, 3)));

        EventBenefits eventBenefits = benefitService.checkApplicableEventBenefits(visitDate, orders);
        Map<Event, Integer> result = eventBenefits.getBenefitDetails();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(
                entry(new ChristmasDDayDiscount(), 3400),
                entry(new SpecialDiscount(), 1000)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"4999, NOTHING", "5000, STAR", "9999, STAR", "10000, TREE", "19999, TREE", "20000, SANTA"})
    @DisplayName("총혜택 금액에 따라 고객에게 적용 가능한 이벤트 배지를 알 수 있다.")
    void checkEventBadge(int totalBenefitAmount, EventBadge expected) {
        EventBadge actual = benefitService.checkEventBadge(totalBenefitAmount);
        assertThat(actual).isEqualTo(expected);
    }
}