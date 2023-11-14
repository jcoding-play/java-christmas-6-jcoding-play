package christmas.controller;

import christmas.converter.Converter;
import christmas.domain.VisitDate;
import christmas.domain.benefit.EventBenefits;
import christmas.domain.benefit.EventBadge;
import christmas.domain.event.Event;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.service.benefit.BenefitService;
import christmas.service.order.OrderMapper;
import christmas.service.order.OrderService;
import christmas.dto.OrderDto;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MainController {
    private static final int MONTH_OF_THE_EVENT = 12;

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final BenefitService benefitService;
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(OrderMapper orderMapper, OrderService orderService, BenefitService benefitService,
                          InputView inputView, OutputView outputView) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.benefitService = benefitService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private <T> T repeatTemplate(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return repeatTemplate(inputReader);
        }
    }

    public void run() {
        outputView.printStartMessage(MONTH_OF_THE_EVENT);
        
        VisitDate visitDate = repeatTemplate(this::selectVisitDate);
        Order order = repeatTemplate(this::placeOrder);

        checkEventBenefits(order, visitDate);
    }

    private VisitDate selectVisitDate() {
        int date = inputView.readDate(MONTH_OF_THE_EVENT);
        return new VisitDate(MONTH_OF_THE_EVENT, date);
    }

    private Order placeOrder() {
        String menuAndCounts = inputView.readMenuAndCount();
        OrderDto orderDto = Converter.toOrderDto(menuAndCounts);

        return orderService.placeOrder(orderMapper, orderDto);
    }

    private void checkEventBenefits(Order order, VisitDate visitDate) {
        outputView.printPreviewBenefitsMessage(visitDate.getMonth(), visitDate.getDate());

        EventBenefits eventBenefits = benefitService.checkApplicableEventBenefits(visitDate, order);
        int totalOrderAmount = order.calculateTotalOrderAmount();
        int totalBenefitAmount = eventBenefits.calculateTotalBenefitAmount();

        showOrderInformation(order, totalOrderAmount);
        showEventBenefits(eventBenefits, totalOrderAmount, totalBenefitAmount);
    }

    private void showOrderInformation(Order order, int totalOrderAmount) {
        OrderDto orderDto = orderMapper.toDto(order);
        outputView.printMenu(orderDto.orderMenus());

        outputView.printTotalOrderAmount(totalOrderAmount);
    }

    private void showEventBenefits(EventBenefits eventBenefits, int totalOrderAmount, int totalBenefitAmount) {
        showGiftMenu(eventBenefits);
        showBenefitDetails(eventBenefits);
        showTotalBenefitAmount(totalBenefitAmount);
        showEstimatedPaymentAmount(eventBenefits, totalOrderAmount, totalBenefitAmount);
        showEventBadge(totalBenefitAmount);
    }

    private void showGiftMenu(EventBenefits eventBenefits) {
        Menu menu = benefitService.getGiftMenu();
        int count = calculateGiftMenuCount(eventBenefits);

        outputView.printGiftMenu(menu.getName(), count);
    }

    private int calculateGiftMenuCount(EventBenefits eventBenefits) {
        if (eventBenefits.isGiftEventApplied()) {
            return 1;
        }
        return 0;
    }

    private void showBenefitDetails(EventBenefits eventBenefits) {
        Map<String, Integer> benefitDetails = toDto(eventBenefits.getBenefitDetails());
        outputView.printBenefitDetails(benefitDetails);
    }

    private void showTotalBenefitAmount(int totalBenefitAmount) {
        outputView.printTotalBenefitAmount(totalBenefitAmount);
    }

    private void showEstimatedPaymentAmount(EventBenefits eventBenefits, int totalOrderAmount, int totalBenefitAmount) {
        int estimatedPaymentAmount = eventBenefits.calculateEstimatedPaymentAmount(totalOrderAmount, totalBenefitAmount);
        outputView.printEstimatedPaymentAmount(estimatedPaymentAmount);
    }

    private void showEventBadge(int totalBenefitAmount) {
        EventBadge eventBadge = benefitService.checkEventBadge(totalBenefitAmount);
        outputView.printEventBadge(eventBadge.getName());
    }

    private Map<String, Integer> toDto(Map<Event, Integer> benefitDetails) {
        return benefitDetails.keySet()
                .stream()
                .collect(Collectors.toMap(
                        Event::getName,
                        benefitDetails::get));
    }
}
