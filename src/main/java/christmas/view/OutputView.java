package christmas.view;

import christmas.service.order.dto.OrderMenuDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_BENEFITS_MESSAGE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE_PREFIX = "<주문 메뉴>";
    private static final String MENU_MESSAGE_FORMAT = "%s %d개";
    private static final String AMOUNT_MESSAGE_FORMAT = "%,d원";
    private static final String TOTAL_ORDER_AMOUNT_MESSAGE_PREFIX = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE_PREFIX = "<증정 메뉴>";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printPreviewBenefitsMessage(int month, int date) {
        System.out.printf(PREVIEW_BENEFITS_MESSAGE_FORMAT, month, date);
    }

    public void printMenu(List<OrderMenuDto> orderMenus) {
        System.out.println(ORDER_MENU_MESSAGE_PREFIX);

        String orderMenuMessage = generateMenuMessages(orderMenus);
        System.out.println(orderMenuMessage);
    }

    private String generateMenuMessages(List<OrderMenuDto> orderMenus) {
        return orderMenus.stream()
                .map(orderMenu -> generateMenuMessage(orderMenu.name(), orderMenu.count()))
                .collect(Collectors.joining(NEWLINE));
    }

    private String generateMenuMessage(String menu, int count) {
        return String.format(MENU_MESSAGE_FORMAT, menu, count);
    }

    public void printTotalOrderAmount(int totalOrderAmount) {
        System.out.println(TOTAL_ORDER_AMOUNT_MESSAGE_PREFIX);

        String totalOrderAmountMessage = generateAmountMessage(totalOrderAmount);
        System.out.println(totalOrderAmountMessage);
    }

    private String generateAmountMessage(int totalOrderAmount) {
        return String.format(AMOUNT_MESSAGE_FORMAT, totalOrderAmount);
    }

    public void printGiftMenu(String giftMenu, int count) {
        System.out.println(GIFT_MENU_MESSAGE_PREFIX);

        String giftMenuMessage = generateMenuMessage(giftMenu, count);
        System.out.println(giftMenuMessage);
    }
}
