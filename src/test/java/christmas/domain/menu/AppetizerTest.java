package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppetizerTest {

    @Test
    @DisplayName("에피타이저에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Appetizer[] values = Appetizer.values();

        assertThat(values).containsExactly(Appetizer.MUSHROOM_SOUP, Appetizer.TAPAS, Appetizer.CAESAR_SALAD);
    }
}