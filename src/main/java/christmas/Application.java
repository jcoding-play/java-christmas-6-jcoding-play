package christmas;

import christmas.controller.MainController;
import christmas.service.benefit.BenefitService;
import christmas.service.order.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        OrderService orderService = applicationConfiguration.orderService();
        BenefitService benefitService = applicationConfiguration.benefitService();
        InputView inputView = applicationConfiguration.inputView();
        OutputView outputView = applicationConfiguration.outputView();

        MainController mainController = new MainController(orderService, benefitService, inputView, outputView);
        mainController.run();
    }
}
