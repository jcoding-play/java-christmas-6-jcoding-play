package christmas.domain.discount;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DiscountEventsTest {

    @Test
    @DisplayName("진행하는 할인 종류를 알 수 있다.")
    void createDiscountEvents() {
        List<DiscountEvent> events = List.of(
                new ChristmasDDayDiscount(),
                new WeekDayDiscount(),
                new WeekEndDiscount(),
                new SpecialDiscount(),
                new GiftEvent());
        DiscountEvents discountEvents = new DiscountEvents(events);

        assertThat(discountEvents).extracting("discountEvents", InstanceOfAssertFactories.list(DiscountEvent.class))
                .hasSize(5);
    }
}