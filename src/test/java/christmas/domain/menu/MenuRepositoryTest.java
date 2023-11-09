package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class MenuRepositoryTest {

    @ParameterizedTest
    @MethodSource("typeAndMenus")
    @DisplayName("타입별 메뉴를 찾을 수 있다.")
    void findByType(Type type, List<Menu> expected) {
        MenuRepository menuRepository = new MenuRepository();
        List<Menu> actual = menuRepository.findByType(type);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> typeAndMenus() {
        return Stream.of(
                arguments(Type.APPETIZER, List.of(Appetizer.MUSHROOM_SOUP, Appetizer.TAPAS, Appetizer.CAESAR_SALAD)),
                arguments(Type.MAIN, List.of(Main.T_BONE_STREAK, Main.BBQ_RIBS, Main.SEAFOOD_PASTA, Main.CHRISTMAS_PASTA)),
                arguments(Type.DESSERT, List.of(Dessert.CHOCOLATE_CAKE, Dessert.ICE_CREAM)),
                arguments(Type.DRINK, List.of(Drink.ZERO_COLA, Drink.RED_WINE, Drink.CHAMPAGNE))
        );
    }
}