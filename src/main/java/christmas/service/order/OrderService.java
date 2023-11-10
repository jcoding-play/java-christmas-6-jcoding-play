package christmas.service.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.service.order.dto.OrderDto;
import christmas.service.order.dto.OrderMenuDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final MenuRepository menuRepository;

    public OrderService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Order placeOrder(OrderDto orderDto) {
        List<OrderMenu> orderMenus = new ArrayList<>();

        for (OrderMenuDto orderMenu : orderDto.orderMenus()) {
            String name = orderMenu.name();
            Optional<Menu> menu = menuRepository.findByName(name);
            if (menu.isEmpty()) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            orderMenus.add(new OrderMenu(menu.get(), orderMenu.count()));
        }

        return new Order(orderMenus);
    }
}
