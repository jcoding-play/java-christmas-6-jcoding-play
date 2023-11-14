package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DrinkTest {

    @Test
    @DisplayName("음료에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Drink[] values = Drink.values();

        assertThat(values).containsExactly(Drink.ZERO_COLA, Drink.RED_WINE, Drink.CHAMPAGNE);
    }
}