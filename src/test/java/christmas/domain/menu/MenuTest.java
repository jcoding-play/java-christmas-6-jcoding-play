package christmas.domain.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MenuTest {
    private Menu appetizer;
    private Menu dessert;
    private Menu drink;
    private Menu main;

    @BeforeEach
    void setUp() {
        appetizer = Appetizer.MUSHROOM_SOUP;
        dessert = Dessert.CHOCOLATE_CAKE;
        drink = Drink.CHAMPAGNE;
        main = Main.T_BONE_STREAK;
    }

    @Test
    @DisplayName("해당 메뉴가 음료인지 알 수 있다.")
    void isDrink() {
        assertThat(appetizer.isDrink()).isFalse();
        assertThat(dessert.isDrink()).isFalse();
        assertThat(drink.isDrink()).isTrue();
        assertThat(main.isDrink()).isFalse();
    }

    @Test
    @DisplayName("해당 메뉴가 디저트인지 알 수 있다.")
    void isDessert() {
        assertThat(appetizer.isDessert()).isFalse();
        assertThat(dessert.isDessert()).isTrue();
        assertThat(drink.isDessert()).isFalse();
        assertThat(main.isDessert()).isFalse();
    }

    @Test
    @DisplayName("해당 메뉴가 메인인지 알 수 있다.")
    void isMain() {
        assertThat(appetizer.isMain()).isFalse();
        assertThat(dessert.isMain()).isFalse();
        assertThat(drink.isMain()).isFalse();
        assertThat(main.isMain()).isTrue();
    }
}