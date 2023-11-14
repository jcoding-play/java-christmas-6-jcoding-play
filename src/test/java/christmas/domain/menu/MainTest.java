package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    @DisplayName("메인 메뉴에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Main[] values = Main.values();

        assertThat(values).containsExactly(Main.T_BONE_STREAK, Main.BBQ_RIBS, Main.SEAFOOD_PASTA, Main.CHRISTMAS_PASTA);
    }
}