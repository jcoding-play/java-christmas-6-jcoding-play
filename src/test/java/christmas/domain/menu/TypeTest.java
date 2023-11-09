package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TypeTest {

    @Test
    @DisplayName("타입은 애피타이저, 메인, 디저트, 음료로 총 4가지로 구성되어있다.")
    void values() {
        Type[] values = Type.values();

        assertThat(values.length).isEqualTo(4);
        assertThat(values).containsExactly(Type.APPETIZER, Type.MAIN, Type.DESSERT, Type.DRINK);
    }
}