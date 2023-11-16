package christmas.domain.benefit;

import christmas.domain.event.*;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
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

    @Test
    @DisplayName("증정 이벤트에 해당하는 경우, 증정 메뉴를 찾을 수 있다.")
    void findGiftMenu_V1() {
        Map<Event, Integer> result = Map.of(new GiftEvent(new GiftMenu(Main.T_BONE_STREAK, 1)), 25000);
        EventBenefits eventBenefits = new EventBenefits(result);

        GiftMenu giftMenu = eventBenefits.findGiftMenu();

        assertThat(giftMenu).isNotNull();
        assertThat(giftMenu.getName()).isEqualTo(Main.T_BONE_STREAK.getName());
    }

    @Test
    @DisplayName("증정 이벤트에 해당하지 않는 경우, 증정 메뉴를 찾을 수 없다.")
    void findGiftMenu_V2() {
        Map<Event, Integer> result = Map.of(new SpecialDiscount(), 1000);
        EventBenefits eventBenefits = new EventBenefits(result);

        GiftMenu giftMenu = eventBenefits.findGiftMenu();

        assertThat(giftMenu).isNull();
    }
}