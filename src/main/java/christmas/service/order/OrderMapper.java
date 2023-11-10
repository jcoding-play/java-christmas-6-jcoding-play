package christmas.service.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.service.order.dto.OrderDto;
import christmas.service.order.dto.OrderMenuDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    private final MenuRepository menuRepository;

    public OrderMapper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Order mapFrom(OrderDto orderDto) {
        List<OrderMenuDto> orderMenus = orderDto.orderMenus();
        return mapOrder(orderMenus);
    }

    private Order mapOrder(List<OrderMenuDto> orderMenus) {
        return orderMenus.stream()
                .map(this::mapOrderMenuFrom)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Order::new));
    }

    private OrderMenu mapOrderMenuFrom(OrderMenuDto orderMenu) {
        Menu menu = menuRepository.findByName(orderMenu.name());
        int count = orderMenu.count();

        return new OrderMenu(menu, count);
    }
}
