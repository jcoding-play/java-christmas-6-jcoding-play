package christmas.view.validator;

import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String INPUT_BLANK_EXCEPTION_MESSAGE = "입력은 공백일 수 없습니다.";
    private static final String NOT_DIGIT_EXCEPTION_MESSAGE = "예상 방문 날짜에 대한 입력은 숫자만 가능합니다.";
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private static final Pattern ONLY_DIGIT = Pattern.compile("-?[0-9]+");
    private static final Pattern VALID_FORMAT = Pattern.compile("[ㄱ-ㅎ가-힣]+-[0-9]+(,[ㄱ-ㅎ가-힣]+-[0-9]+)*");

    protected static void validateInput(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(INPUT_BLANK_EXCEPTION_MESSAGE);
        }
    }

    public static void validateDate(String input) {
        validateInput(input);

        if (isNotDigit(input)) {
            throw new IllegalArgumentException(NOT_DIGIT_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isNotDigit(String input) {
        return !ONLY_DIGIT.matcher(input)
                .matches();
    }

    public static void validateMenuAndCount(String input) {
        validateInput(input);

        if (isInvalidFormat(input)) {
            throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isInvalidFormat(String input) {
        return !VALID_FORMAT.matcher(input)
                .matches();
    }
}
