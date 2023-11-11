package christmas.service.benefit;

import christmas.domain.BenefitDetails;
import christmas.domain.VisitDate;
import christmas.domain.event.*;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        VisitDate visitDate = new VisitDate(25);
        Order order = new Order(List.of(new OrderMenu(Main.T_BONE_STREAK, 1), new OrderMenu(Drink.ZERO_COLA, 3)));

        BenefitDetails benefitDetails = benefitService.checkApplicableBenefitDetails(visitDate, order);
        Map<Event, Integer> result = benefitDetails.getBenefitDetails();

        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(
                entry(new ChristmasDDayDiscount(), 3400),
                entry(new WeekDayDiscount(), 0),
                entry(new SpecialDiscount(), 1000)
        );
    }
}