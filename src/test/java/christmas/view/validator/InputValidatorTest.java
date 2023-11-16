package christmas.view.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력이 null 이거나 공백이면 예외가 발생한다.")
    void validateInput(String input) {
        assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 공백일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "!", "12A"})
    @DisplayName("예상 방문 날짜에 대한 입력이 숫자가 아니면 예외가 발생한다.")
    void validateDate(String input) {
        assertThatThrownBy(() -> InputValidator.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"tapas-1", "타파스=1", "타파스,1", "타파스-one", "타파스-1-제로콜라-1", "타파스-1,,제로콜라-1"})
    @DisplayName("메뉴 입력 형식이 올바르지 않으면 예외가 발생한다.")
    void validateMenuAndCount(String input) {
        assertThatThrownBy(() -> InputValidator.validateMenuAndCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}