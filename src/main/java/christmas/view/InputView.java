package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.validator.InputValidator;

public class InputView {
    private static final String INPUT_DATE_MESSAGE_FORMAT = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    private static final String INPUT_MENU_AND_COUNT_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int readDate(int month) {
        System.out.printf(INPUT_DATE_MESSAGE_FORMAT, month);
        String input = readLine();

        InputValidator.validateDate(input);
        return Integer.parseInt(input);
    }

    public String readMenuAndCount() {
        System.out.println(INPUT_MENU_AND_COUNT_MESSAGE);
        String input = readLine();

        InputValidator.validateMenuAndCount(input);
        return input;
    }

    private String readLine() {
        return Console.readLine();
    }
}
