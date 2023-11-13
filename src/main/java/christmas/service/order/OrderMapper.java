package christmas.service.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.dto.OrderDto;
import christmas.dto.OrderMenuDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    private final MenuRepository menuRepository;

    public OrderMapper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Order toEntity(OrderDto orderDto) {
        List<OrderMenuDto> orderMenus = orderDto.orderMenus();
        return mapOrder(orderMenus);
    }

    private Order mapOrder(List<OrderMenuDto> orderMenus) {
        return orderMenus.stream()
                .map(this::mapOrderMenu)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Order::new));
    }

    private OrderMenu mapOrderMenu(OrderMenuDto orderMenu) {
        Menu menu = menuRepository.findByName(orderMenu.name());
        int count = orderMenu.count();

        return new OrderMenu(menu, count);
    }

    public OrderDto toDto(Order order) {
        List<OrderMenu> orderMenus = order.getOrderMenus();
        return mapOrderDto(orderMenus);
    }

    private OrderDto mapOrderDto(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(this::mapOrderMenuDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), OrderDto::new));
    }

    private OrderMenuDto mapOrderMenuDto(OrderMenu orderMenu) {
        Menu menu = orderMenu.getMenu();
        int count = orderMenu.getCount();

        return new OrderMenuDto(menu.getName(), count);
    }
}
