package christmas.domain;

import christmas.domain.event.ChristmasDDayDiscount;
import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;
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
}