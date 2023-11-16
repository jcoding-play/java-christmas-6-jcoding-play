package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    @DisplayName("메인 메뉴에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Main[] values = Main.values();

        assertThat(values).containsExactly(Main.T_BONE_STREAK, Main.BBQ_RIBS, Main.SEAFOOD_PASTA, Main.CHRISTMAS_PASTA);
    }

    @ParameterizedTest
    @CsvSource(value = {"T_BONE_STREAK, 티본스테이크, true", "T_BONE_STREAK, 스테이크, false"})
    @DisplayName("해당 메뉴의 이름과 입력된 이름이 같은지 판단할 수 있다.")
    void isMatchName(Main main, String name, boolean expected) {
        boolean result = main.isMatchName(name);
        assertThat(result).isEqualTo(expected);
    }
}