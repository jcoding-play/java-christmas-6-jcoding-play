package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DessertTest {

    @Test
    @DisplayName("디저트에 해당하는 메뉴를 알 수 있다.")
    void values() {
        Dessert[] values = Dessert.values();

        assertThat(values).containsExactly(Dessert.CHOCOLATE_CAKE, Dessert.ICE_CREAM);
    }
}