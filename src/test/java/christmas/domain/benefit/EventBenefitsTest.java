package christmas.domain.benefit;

import christmas.domain.event.*;
import christmas.domain.menu.Drink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EventBenefitsTest {

    @Test
    @DisplayName("총혜택 금액을 알 수 있다.")
    void calculateTotalBenefitAmount() {
        Map<Event, Integer> result = Map.of(
                new GiftEvent(new GiftMenu(Drink.CHAMPAGNE, 1)), 25000,
                new ChristmasDDayDiscount(), 3400);
        EventBenefits eventBenefits = new EventBenefits(result);

        int totalBenefitAmount = eventBenefits.calculateTotalBenefitAmount();

        assertThat(totalBenefitAmount).isEqualTo(28400);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 알 수 있다.")
    void calculateEstimatedPaymentAmount() {
        Map<Event, Integer> result = Map.of(
                new ChristmasDDayDiscount(), 1200, new WeekDayDiscount(), 4046,
                new SpecialDiscount(), 1000, new GiftEvent(new GiftMenu(Drink.CHAMPAGNE, 1)), 25000);
        EventBenefits eventBenefits = new EventBenefits(result);

        int totalOrderPrice = 142000;
        int estimatedPaymentAmount = eventBenefits.calculateEstimatedPaymentAmount(totalOrderPrice);

        assertThat(estimatedPaymentAmount).isEqualTo(135754);
    }
}