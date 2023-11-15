package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AppetizerTest {

    @Test
    @DisplayName("에피타이저에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Appetizer[] values = Appetizer.values();

        assertThat(values).containsExactly(Appetizer.MUSHROOM_SOUP, Appetizer.TAPAS, Appetizer.CAESAR_SALAD);
    }

    @ParameterizedTest
    @CsvSource(value = {"TAPAS, 타파스, true", "TAPAS, 토포스, false"})
    @DisplayName("해당 메뉴의 이름과 입력된 이름이 같은지 판단할 수 있다.")
    void isMatchName(Appetizer appetizer, String name, boolean expected) {
        boolean result = appetizer.isMatchName(name);
        assertThat(result).isEqualTo(expected);
    }
}