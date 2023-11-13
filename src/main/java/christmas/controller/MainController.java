package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.benefit.BenefitDetails;
import christmas.domain.benefit.EventBadge;
import christmas.domain.event.Event;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.service.benefit.BenefitService;
import christmas.service.order.OrderService;
import christmas.service.order.dto.OrderDto;
import christmas.service.order.dto.OrderMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainController {
    private final OrderService orderService;
    private final BenefitService benefitService;
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(OrderService orderService, BenefitService benefitService, InputView inputView, OutputView outputView) {
        this.orderService = orderService;
        this.benefitService = benefitService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        int date = inputView.readDate();
        VisitDate visitDate = new VisitDate(date);

        String menuAndCounts = inputView.readMenuAndCount();
        OrderDto orderDto = toDto(menuAndCounts);

        Order order = orderService.placeOrder(orderDto);

        showEventBenefits(order, visitDate);
    }

    private void showEventBenefits(Order order, VisitDate visitDate) {
        outputView.printPreviewBenefitsMessage(visitDate.getMonth(), visitDate.getDate());

        List<OrderMenuDto> orderMenus = toDto(order.getOrderMenus());
        outputView.printMenu(orderMenus);

        int totalOrderPrice = order.calculateTotalOrderPrice();
        outputView.printTotalOrderAmount(totalOrderPrice);

        BenefitDetails benefitDetails = benefitService.checkApplicableBenefitDetails(visitDate, order);

        String giftMenu = Drink.CHAMPAGNE.getName();
        int count = 1;
        outputView.printGiftMenu(giftMenu, count);

        Map<String, Integer> benefits = toDto(benefitDetails.getBenefitDetails());
        outputView.printBenefitDetails(benefits);

        int totalBenefitAmount = benefitDetails.calculateTotalBenefitAmount();
        outputView.printTotalBenefitAmount(totalBenefitAmount);

        int estimatedPaymentAmount = benefitDetails.calculateEstimatedPaymentAmount(totalOrderPrice, totalBenefitAmount);
        outputView.printEstimatedPaymentAmount(estimatedPaymentAmount);

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

    private OrderDto toDto(String menuAndCounts) {
        List<OrderMenuDto> orderMenus = new ArrayList<>();

        for (String menuAndCount : menuAndCounts.split(",")) {
            String[] input = menuAndCount.split("-");
            orderMenus.add(new OrderMenuDto(input[0], Integer.parseInt(input[1])));
        }

        return new OrderDto(orderMenus);
    }

    private List<OrderMenuDto> toDto(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(this::generateOrderMenuDto)
                .toList();
    }

    private OrderMenuDto generateOrderMenuDto(OrderMenu orderMenu) {
        Menu menu = orderMenu.getMenu();
        int count = orderMenu.getCount();

        return new OrderMenuDto(menu.getName(), count);
    }
}
