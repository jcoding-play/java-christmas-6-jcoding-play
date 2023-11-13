package christmas.view;

import christmas.service.order.dto.OrderMenuDto;

import java.util.List;
import java.util.Map;
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
    private static final String BENEFIT_DETAILS_MESSAGE_FORMAT = "%s: -%s";
    private static final String BENEFIT_DETAILS_MESSAGE_PREFIX = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE_PREFIX = "<총혜택 금액>";
    private static final String ESTIMATED_PAYMENT_AMOUNT_MESSAGE_PREFIX = "<할인 후 예상 결제 금액>";

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

    private String generateAmountMessage(int amount) {
        return String.format(AMOUNT_MESSAGE_FORMAT, amount);
    }

    public void printGiftMenu(String giftMenu, int count) {
        System.out.println(GIFT_MENU_MESSAGE_PREFIX);

        String giftMenuMessage = generateMenuMessage(giftMenu, count);
        System.out.println(giftMenuMessage);
    }

    public void printBenefitDetails(Map<String, Integer> benefitDetails) {
        System.out.println(BENEFIT_DETAILS_MESSAGE_PREFIX);

        String benefitDetailsMessage = generateBenefitDetailsMessages(benefitDetails);
        System.out.println(benefitDetailsMessage);
    }

    private String generateBenefitDetailsMessages(Map<String, Integer> benefitDetails) {
        return benefitDetails.keySet()
                .stream()
                .map(eventName -> generateBenefitDetailsMessage(eventName, benefitDetails.get(eventName)))
                .collect(Collectors.joining(NEWLINE));
    }

    private String generateBenefitDetailsMessage(String event, int discountedAmount) {
        String discountedAmountMessage = generateAmountMessage(discountedAmount);
        return String.format(BENEFIT_DETAILS_MESSAGE_FORMAT, event, discountedAmountMessage);
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE_PREFIX);

        String totalBenefitAmountMessage = generateAmountMessage(totalBenefitAmount * -1);
        System.out.println(totalBenefitAmountMessage);
    }

    public void printEstimatedPaymentAmount(int estimatedPaymentAmount) {
        System.out.println(ESTIMATED_PAYMENT_AMOUNT_MESSAGE_PREFIX);

        String estimatedPaymentAmountMessage = generateAmountMessage(estimatedPaymentAmount);
        System.out.println(estimatedPaymentAmountMessage);
    }
}
