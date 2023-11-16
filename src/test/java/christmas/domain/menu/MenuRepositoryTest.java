package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MenuRepositoryTest {

    @ParameterizedTest
    @CsvSource(value = {"타파스, TAPAS", "양송이수프, MUSHROOM_SOUP"})
    @DisplayName("이름을 통해 에피타이저 메뉴를 찾을 수 있다.")
    void findByName_Appetizer(String name, Appetizer expected) {
        MenuRepository menuRepository = new MenuRepository();
        Menu actual = menuRepository.findByName(name);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"초코케이크, CHOCOLATE_CAKE", "아이스크림, ICE_CREAM"})
    @DisplayName("이름을 통해 디저트 메뉴를 찾을 수 있다.")
    void findByName_Dessert(String name, Dessert expected) {
        MenuRepository menuRepository = new MenuRepository();
        Menu actual = menuRepository.findByName(name);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"바비큐립, BBQ_RIBS", "해산물파스타, SEAFOOD_PASTA"})
    @DisplayName("이름을 통해 메인 메뉴를 찾을 수 있다.")
    void findByName_Main(String name, Main expected) {
        MenuRepository menuRepository = new MenuRepository();
        Menu actual = menuRepository.findByName(name);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"샴페인, CHAMPAGNE", "제로콜라, ZERO_COLA"})
    @DisplayName("이름을 통해 음료 메뉴를 찾을 수 있다.")
    void findByName_Drink(String name, Drink expected) {
        MenuRepository menuRepository = new MenuRepository();
        Menu actual = menuRepository.findByName(name);

        assertThat(actual).isEqualTo(expected);
    }
}