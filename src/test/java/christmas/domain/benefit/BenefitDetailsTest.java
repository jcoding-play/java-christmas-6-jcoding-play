package christmas.domain.benefit;

import christmas.domain.event.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BenefitDetailsTest {

    @Test
    @DisplayName("총혜택 금액을 알 수 있다.")
    void calculateTotalBenefitAmount() {
        Map<Event, Integer> result = Map.of(new GiftEvent(), 25000, new ChristmasDDayDiscount(), 3400);
        BenefitDetails benefitDetails = new BenefitDetails(result);

        int totalBenefitAmount = benefitDetails.calculateTotalBenefitAmount();

        assertThat(totalBenefitAmount).isEqualTo(28400);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 알 수 있다.")
    void calculateEstimatedPaymentAmount() {
        Map<Event, Integer> result = Map.of(
                new ChristmasDDayDiscount(), 1200, new WeekDayDiscount(), 4046,
                new SpecialDiscount(), 1000, new GiftEvent(), 25000);
        BenefitDetails benefitDetails = new BenefitDetails(result);

        int totalOrderPrice = 142000;
        int totalBenefitAmount = 31246;
        int estimatedPaymentAmount = benefitDetails.calculateEstimatedPaymentAmount(totalOrderPrice, totalBenefitAmount);

        assertThat(estimatedPaymentAmount).isEqualTo(135754);
    }
}