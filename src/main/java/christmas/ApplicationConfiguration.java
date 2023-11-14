package christmas;

import christmas.domain.event.*;
import christmas.domain.menu.Drink;
import christmas.domain.order.OrderValidator;
import christmas.domain.menu.MenuRepository;
import christmas.service.benefit.BenefitService;
import christmas.service.benefit.Events;
import christmas.service.order.OrderMapper;
import christmas.service.order.OrderService;
import christmas.utils.Constants;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class ApplicationConfiguration {

    public OrderMapper orderMapper() {
        return new OrderMapper(menuRepository());
    }

    private MenuRepository menuRepository() {
        return new MenuRepository();
    }

    public OrderService orderService() {
        return new OrderService(orderValidator());
    }

    private OrderValidator orderValidator() {
        return new OrderValidator();
    }

    public BenefitService benefitService() {
        return new BenefitService(events());
    }

    private Events events() {
        return new Events(findApplicableEvents());
    }

    private List<Event> findApplicableEvents() {
        return List.of(
                new ChristmasDDayDiscount(),
                new WeekDayDiscount(),
                new WeekEndDiscount(),
                new SpecialDiscount(),
                new GiftEvent(giftMenu())
        );
    }

    private GiftMenu giftMenu() {
        return new GiftMenu(Drink.CHAMPAGNE, Constants.MINIMUM_COUNT);
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
