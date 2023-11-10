package christmas.service.benefit;

import christmas.domain.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

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
    @DisplayName("총 주문 금액이 12만원 미만이면 증정 이벤트는 포함되지 않는다.")
    void findApplicableEvents_V1() {
        List<Event> result = benefitService.findApplicableEvents(25, 119999);

        assertThat(result).containsExactly(
                new ChristmasDDayDiscount(), new WeekDayDiscount(), new SpecialDiscount());
    }

    @Test
    @DisplayName("총 주문 금액이 12만원 이상이면 증정 이벤트는 포함된다.")
    void findApplicableEvents_V2() {
        List<Event> result = benefitService.findApplicableEvents(25, 120000);

        assertThat(result).containsExactly(
                new ChristmasDDayDiscount(), new WeekDayDiscount(), new SpecialDiscount(), new GiftEvent());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("방문하는 날짜가 금요일 또는 토요일이라면 주말 할인이 적용된다.")
    void findApplicableEvents_V3(int date) {
        List<Event> result = benefitService.findApplicableEvents(date, 110000);

        assertThat(result).containsExactly(new ChristmasDDayDiscount(), new WeekEndDiscount());
    }

    @Test
    @DisplayName("26일 이후부터는 크리스마스 디데이 할인이 적용되지 않는다.")
    void findApplicableEvents_V4() {
        List<Event> result = benefitService.findApplicableEvents(26, 120000);

        assertThat(result).containsExactly(new WeekDayDiscount(), new GiftEvent());
    }

    @Test
    @DisplayName("이벤트 달력에 별이 있으면 특별 할인이 적용된다.")
    void findApplicableEvents_V5() {
        List<Event> result = benefitService.findApplicableEvents(31, 10000);

        assertThat(result).containsExactly(new WeekDayDiscount(), new SpecialDiscount());
    }

    @Test
    @DisplayName("총 주문 금액이 10,000원보다 작다면 어떤 이벤트도 적용되지 않는다.")
    void findApplicableEvents_V6() {
        List<Event> result = benefitService.findApplicableEvents(31, 9999);

        assertThat(result).isEmpty();
    }
}