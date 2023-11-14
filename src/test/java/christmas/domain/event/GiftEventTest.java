package christmas.domain.event;

import christmas.domain.menu.Drink;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GiftEventTest {

    @Test
    @DisplayName("할인 적용 가능한 날짜를 알 수 있다.")
    void findApplicableDates() {
        GiftEvent giftEvent = new GiftEvent(new GiftMenu(Drink.CHAMPAGNE, 1));

        assertThat(giftEvent).extracting("applicableDates", InstanceOfAssertFactories.list(Integer.class))
                .containsExactly(
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
    }
}