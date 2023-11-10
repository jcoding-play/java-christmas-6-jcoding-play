package christmas.domain.event;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EventsTest {

    @Test
    @DisplayName("진행하는 이벤트 종류를 알 수 있다.")
    void findEvents() {
        List<Event> eventList = List.of(
                new ChristmasDDayDiscount(),
                new WeekDayDiscount(),
                new WeekEndDiscount(),
                new SpecialDiscount(),
                new GiftEvent());
        Events events = new Events(eventList);

        assertThat(events).extracting("events", InstanceOfAssertFactories.list(Event.class))
                .hasSize(5);
    }
}