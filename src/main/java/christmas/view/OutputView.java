package christmas.view;

import christmas.dto.EventBenefitsDto;
import christmas.dto.GiftMenuDto;
import christmas.dto.OrderDto;
import christmas.dto.OrdersDto;
import christmas.utils.Constants;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();

    private static final String START_MESSAGE_FORMAT = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다." + NEWLINE;
    private static final String PREVIEW_BENEFITS_MESSAGE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + NEWLINE;
    private static final String MENU_MESSAGE_FORMAT = "%s %d개";
    private static final String AMOUNT_MESSAGE_FORMAT = "%,d원";
    private static final String BENEFIT_DETAILS_MESSAGE_FORMAT = "%s: %s";
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s" + NEWLINE;

    private static final String PREFIX_SYMBOL_OF_PREFIX_MESSAGE = "<";
    private static final String SUFFIX_SYMBOL_OF_PREFIX_MESSAGE = ">";
    private static final String ORDER_MENU_MESSAGE = "주문 메뉴";
    private static final String TOTAL_ORDER_AMOUNT_MESSAGE = "할인 전 총주문 금액";
    private static final String GIFT_MENU_MESSAGE = "증정 메뉴";
    private static final String BENEFIT_DETAILS_MESSAGE = "혜택 내역";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "총혜택 금액";
    private static final String ESTIMATED_PAYMENT_AMOUNT_MESSAGE = "할인 후 예상 결제 금액";
    private static final String EVENT_BADGE_MESSAGE = "12월 이벤트 배지";

    private static final String NOTHING_MESSAGE = "없음";
    private static final int NEGATIVE_NUMBER = -1;

    public void printStartMessage(int month) {
        System.out.printf(START_MESSAGE_FORMAT, month);
    }

    public void printPreviewBenefitsMessage(int month, int date) {
        System.out.printf(PREVIEW_BENEFITS_MESSAGE_FORMAT, month, date);
    }

    public void printMenu(OrdersDto orders) {
        printTemplate(ORDER_MENU_MESSAGE, () -> generateMenuMessages(orders.orders()));
    }

    private String generateMenuMessages(List<OrderDto> orders) {
        return orders.stream()
                .map(orderMenu -> generateMenuMessage(orderMenu.name(), orderMenu.count()))
                .collect(Collectors.joining(NEWLINE));
    }

    private String generateMenuMessage(String menu, int count) {
        return String.format(MENU_MESSAGE_FORMAT, menu, count);
    }

    public void printTotalOrderAmount(int totalOrderAmount) {
        printTemplate(TOTAL_ORDER_AMOUNT_MESSAGE, () -> generateAmountMessage(totalOrderAmount));
    }

    private String generateAmountMessage(int amount) {
        return String.format(AMOUNT_MESSAGE_FORMAT, amount);
    }

    public void printGiftMenu(GiftMenuDto giftMenu) {
        printTemplate(GIFT_MENU_MESSAGE, () -> generateGiftMenuMessage(giftMenu.name(), giftMenu.count()));
    }

    private String generateGiftMenuMessage(String giftMenu, int count) {
        if (count == Constants.NUMBER_OF_GIFT_EVENTS_NOT_APPLIED) {
            return NOTHING_MESSAGE;
        }
        return generateMenuMessage(giftMenu, count);
    }

    public void printBenefitDetails(EventBenefitsDto eventBenefits) {
        printTemplate(BENEFIT_DETAILS_MESSAGE, () -> generateBenefitDetailsMessages(eventBenefits.benefitDetails()));
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
        String discountedAmountMessage = generateAmountMessage(discountedAmount * NEGATIVE_NUMBER);
        return String.format(BENEFIT_DETAILS_MESSAGE_FORMAT, event, discountedAmountMessage);
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        printTemplate(TOTAL_BENEFIT_AMOUNT_MESSAGE, () -> generateAmountMessage(totalBenefitAmount * NEGATIVE_NUMBER));
    }

    public void printEstimatedPaymentAmount(int estimatedPaymentAmount) {
        printTemplate(ESTIMATED_PAYMENT_AMOUNT_MESSAGE, () -> generateAmountMessage(estimatedPaymentAmount));
    }

    public void printEventBadge(String name) {
        printTemplate(EVENT_BADGE_MESSAGE, () -> name);
    }

    private void printTemplate(String message, Supplier<String> supplier) {
        String prefixMessage = generatePrefixMessage(message);
        String resultMessage = supplier.get();

        System.out.println(join(prefixMessage, resultMessage));
    }

    private String generatePrefixMessage(String message) {
        return NEWLINE + PREFIX_SYMBOL_OF_PREFIX_MESSAGE + message + SUFFIX_SYMBOL_OF_PREFIX_MESSAGE;
    }

    private String join(String prefixMessage, String resultMessage) {
        return String.join(NEWLINE, prefixMessage, resultMessage);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf(ERROR_MESSAGE_FORMAT, errorMessage);
    }
}
