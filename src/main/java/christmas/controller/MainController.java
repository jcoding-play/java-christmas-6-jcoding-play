package christmas.controller;

import christmas.converter.Converter;
import christmas.domain.VisitDate;
import christmas.domain.benefit.EventBadge;
import christmas.domain.benefit.EventBenefits;
import christmas.domain.event.GiftMenu;
import christmas.domain.order.Orders;
import christmas.dto.EventBenefitsDto;
import christmas.dto.GiftMenuDto;
import christmas.dto.OrdersDto;
import christmas.service.benefit.BenefitService;
import christmas.service.order.OrderMapper;
import christmas.service.order.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.function.Supplier;

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
        Orders orders = repeatTemplate(this::placeOrder);

        checkEventBenefits(orders, visitDate);
    }

    private VisitDate selectVisitDate() {
        int date = inputView.readDate(MONTH_OF_THE_EVENT);
        return new VisitDate(MONTH_OF_THE_EVENT, date);
    }

    private Orders placeOrder() {
        String menuAndCounts = inputView.readMenuAndCount();
        OrdersDto ordersDto = Converter.toOrdersDto(menuAndCounts);

        return orderService.placeOrder(orderMapper, ordersDto);
    }

    private void checkEventBenefits(Orders orders, VisitDate visitDate) {
        EventBenefits eventBenefits = checkApplicableEventBenefits(orders, visitDate);
        int totalOrderAmount = orders.calculateTotalOrderAmount();
        int totalBenefitAmount = eventBenefits.calculateTotalBenefitAmount();

        showOrderInformation(orders, totalOrderAmount);
        showEventBenefits(eventBenefits, totalOrderAmount, totalBenefitAmount);
    }

    private EventBenefits checkApplicableEventBenefits(Orders orders, VisitDate visitDate) {
        outputView.printPreviewBenefitsMessage(visitDate.getMonth(), visitDate.getDate());

        return benefitService.checkApplicableEventBenefits(visitDate, orders);
    }

    private void showOrderInformation(Orders orders, int totalOrderAmount) {
        OrdersDto ordersDto = orderMapper.toDto(orders);
        outputView.printMenu(ordersDto);

        outputView.printTotalOrderAmount(totalOrderAmount);
    }

    private void showEventBenefits(EventBenefits eventBenefits, int totalOrderAmount, int totalBenefitAmount) {
        showGiftMenu(eventBenefits);
        showBenefitDetails(eventBenefits);
        showTotalBenefitAmount(totalBenefitAmount);
        showEstimatedPaymentAmount(eventBenefits, totalOrderAmount);
        showEventBadge(totalBenefitAmount);
    }

    private void showGiftMenu(EventBenefits eventBenefits) {
        GiftMenu giftMenu = eventBenefits.findGiftMenu();
        GiftMenuDto giftMenuDto = GiftMenuDto.from(giftMenu);

        outputView.printGiftMenu(giftMenuDto);
    }

    private void showBenefitDetails(EventBenefits eventBenefits) {
        EventBenefitsDto eventBenefitsDto = EventBenefitsDto.from(eventBenefits);
        outputView.printBenefitDetails(eventBenefitsDto);
    }

    private void showTotalBenefitAmount(int totalBenefitAmount) {
        outputView.printTotalBenefitAmount(totalBenefitAmount);
    }

    private void showEstimatedPaymentAmount(EventBenefits eventBenefits, int totalOrderAmount) {
        int estimatedPaymentAmount = eventBenefits.calculateEstimatedPaymentAmount(totalOrderAmount);
        outputView.printEstimatedPaymentAmount(estimatedPaymentAmount);
    }

    private void showEventBadge(int totalBenefitAmount) {
        EventBadge eventBadge = benefitService.checkEventBadge(totalBenefitAmount);
        outputView.printEventBadge(eventBadge.getName());
    }
}
