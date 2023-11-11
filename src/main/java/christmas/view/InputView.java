package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.validator.InputValidator;

public class InputView {
    private static final String INPUT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int readDate() {
        System.out.println(INPUT_DATE_MESSAGE);
        String input = Console.readLine();

        InputValidator.validateDate(input);
        return Integer.parseInt(input);
    }
}
