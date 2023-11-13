package christmas.view;

import christmas.dto.OrderMenuDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();
    private static final String NOTHING_MESSAGE = "없음";

    private static final String START_MESSAGE_FORMAT = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다." + NEWLINE;
    private static final String PREVIEW_BENEFITS_MESSAGE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + NEWLINE;
    private static final String MENU_MESSAGE_FORMAT = "%s %d개";
    private static final String AMOUNT_MESSAGE_FORMAT = "%,d원";
    private static final String BENEFIT_DETAILS_MESSAGE_FORMAT = "%s: -%s";
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s" + NEWLINE;

    private static final String ORDER_MENU_MESSAGE_PREFIX = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_MESSAGE_PREFIX = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE_PREFIX = "<증정 메뉴>";
    private static final String BENEFIT_DETAILS_MESSAGE_PREFIX = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE_PREFIX = "<총혜택 금액>";
    private static final String ESTIMATED_PAYMENT_AMOUNT_MESSAGE_PREFIX = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_MESSAGE_PREFIX = "<12월 이벤트 배지>";

    public void printStartMessage(int month) {
        System.out.printf(START_MESSAGE_FORMAT, month);
    }

    public void printPreviewBenefitsMessage(int month, int date) {
        System.out.printf(PREVIEW_BENEFITS_MESSAGE_FORMAT, month, date);
    }

    public void printMenu(List<OrderMenuDto> orderMenus) {
        String prefixMessage = generatePrefixMessage(ORDER_MENU_MESSAGE_PREFIX);
        String orderMenuMessage = generateMenuMessages(orderMenus);

        System.out.println(join(prefixMessage, orderMenuMessage));
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
        String prefixMessage = generatePrefixMessage(TOTAL_ORDER_AMOUNT_MESSAGE_PREFIX);
        String totalOrderAmountMessage = generateAmountMessage(totalOrderAmount);

        System.out.println(join(prefixMessage, totalOrderAmountMessage));
    }

    private String generateAmountMessage(int amount) {
        return String.format(AMOUNT_MESSAGE_FORMAT, amount);
    }

    public void printGiftMenu(String giftMenu, int count) {
        String prefixMessage = generatePrefixMessage(GIFT_MENU_MESSAGE_PREFIX);
        String giftMenuMessage = generateGiftMenuMessage(giftMenu, count);

        System.out.println(join(prefixMessage, giftMenuMessage));
    }

    private String generateGiftMenuMessage(String giftMenu, int count) {
        if (count == 0) {
            return NOTHING_MESSAGE;
        }
        return generateMenuMessage(giftMenu, count);
    }

    public void printBenefitDetails(Map<String, Integer> benefitDetails) {
        String prefixMessage = generatePrefixMessage(BENEFIT_DETAILS_MESSAGE_PREFIX);
        String benefitDetailsMessage = generateBenefitDetailsMessages(benefitDetails);

        System.out.println(join(prefixMessage, benefitDetailsMessage));
    }

    private String generateBenefitDetailsMessages(Map<String, Integer> benefitDetails) {
        if (benefitDetails.isEmpty()) {
            return NOTHING_MESSAGE;
        }
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
        String prefixMessage = generatePrefixMessage(TOTAL_BENEFIT_AMOUNT_MESSAGE_PREFIX);
        String totalBenefitAmountMessage = generateAmountMessage(totalBenefitAmount * -1);

        System.out.println(join(prefixMessage, totalBenefitAmountMessage));
    }

    public void printEstimatedPaymentAmount(int estimatedPaymentAmount) {
        String prefixMessage = generatePrefixMessage(ESTIMATED_PAYMENT_AMOUNT_MESSAGE_PREFIX);
        String estimatedPaymentAmountMessage = generateAmountMessage(estimatedPaymentAmount);

        System.out.println(join(prefixMessage, estimatedPaymentAmountMessage));
    }

    public void printEventBadge(String name) {
        String prefixMessage = generatePrefixMessage(EVENT_BADGE_MESSAGE_PREFIX);

        System.out.println(join(prefixMessage, name));
    }

    private String generatePrefixMessage(String prefix) {
        return NEWLINE + prefix;
    }

    private String join(String prefix, String message) {
        return String.join(NEWLINE, prefix, message);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf(ERROR_MESSAGE_FORMAT, errorMessage);
    }
}
