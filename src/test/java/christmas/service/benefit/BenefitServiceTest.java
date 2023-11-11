package christmas.service.benefit;

import christmas.domain.event.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

class BenefitServiceTest {
    private BenefitService benefitService;

    @BeforeEach
    void setUp() {
        List<Event> events = List.of(new ChristmasDDayDiscount(),
                new WeekDayDiscount(), new WeekEndDiscount(),
                new SpecialDiscount(), new GiftEvent());
        benefitService = new BenefitService(new Events(events));
    }
}