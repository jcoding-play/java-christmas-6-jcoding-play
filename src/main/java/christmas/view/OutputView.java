package christmas.view;

import christmas.service.order.dto.OrderMenuDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_BENEFITS_MESSAGE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE_PREFIX = "<주문 메뉴>";
    private static final String ORDER_MENU_MESSAGE_FORMAT = "%s %d개";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printPreviewBenefitsMessage(int month, int date) {
        System.out.printf(PREVIEW_BENEFITS_MESSAGE_FORMAT, month, date);
    }

    public void printMenu(List<OrderMenuDto> orderMenus) {
        System.out.println(ORDER_MENU_MESSAGE_PREFIX);

        String orderMenuMessage = generateOrderMenuMessages(orderMenus);
        System.out.println(orderMenuMessage);
    }

    private String generateOrderMenuMessages(List<OrderMenuDto> orderMenus) {
        return orderMenus.stream()
                .map(this::generateOrderMenuMessage)
                .collect(Collectors.joining(NEWLINE));
    }

    private String generateOrderMenuMessage(OrderMenuDto orderMenu) {
        return String.format(ORDER_MENU_MESSAGE_FORMAT, orderMenu.name(), orderMenu.count());
    }
}
