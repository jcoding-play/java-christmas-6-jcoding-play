package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DessertTest {

    @Test
    @DisplayName("디저트에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Dessert[] values = Dessert.values();

        assertThat(values).containsExactly(Dessert.CHOCOLATE_CAKE, Dessert.ICE_CREAM);
    }

    @ParameterizedTest
    @CsvSource(value = {"ICE_CREAM, 아이스크림, true", "ICE_CREAM, 아이스, false"})
    @DisplayName("해당 메뉴의 이름과 입력된 이름이 같은지 판단할 수 있다.")
    void isMatchName(Dessert dessert, String name, boolean expected) {
        boolean result = dessert.isMatchName(name);
        assertThat(result).isEqualTo(expected);
    }
}