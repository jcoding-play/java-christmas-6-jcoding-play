package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DrinkTest {

    @Test
    @DisplayName("음료에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Drink[] values = Drink.values();

        assertThat(values).containsExactly(Drink.ZERO_COLA, Drink.RED_WINE, Drink.CHAMPAGNE);
    }

    @ParameterizedTest
    @CsvSource(value = {"ZERO_COLA, 제로콜라, true", "ZERO_COLA, 그냥콜라, false"})
    @DisplayName("해당 메뉴의 이름과 입력된 이름이 같은지 판단할 수 있다.")
    void isMatchName(Drink drink, String name, boolean expected) {
        boolean result = drink.isMatchName(name);
        assertThat(result).isEqualTo(expected);
    }
}