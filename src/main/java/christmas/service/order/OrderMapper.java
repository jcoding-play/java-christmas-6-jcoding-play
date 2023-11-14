package christmas.service.order;

import christmas.domain.menu.Menu;
import christmas.domain.order.Orders;
import christmas.domain.order.Order;
import christmas.dto.OrdersDto;
import christmas.dto.OrderDto;
import christmas.domain.menu.MenuRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    private final MenuRepository menuRepository;

    public OrderMapper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Orders toEntity(OrdersDto ordersDto) {
        List<OrderDto> orders = ordersDto.orders();
        return mapOrder(orders);
    }

    private Orders mapOrder(List<OrderDto> orders) {
        return orders.stream()
                .map(this::mapOrder)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Orders::new));
    }

    private Order mapOrder(OrderDto order) {
        Menu menu = menuRepository.findByName(order.name());
        int count = order.count();

        return new Order(menu, count);
    }

    public OrdersDto toDto(Orders order) {
        List<Order> orders = order.getOrders();
        return mapOrderDto(orders);
    }

    private OrdersDto mapOrderDto(List<Order> orders) {
        return orders.stream()
                .map(this::mapOrderDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), OrdersDto::new));
    }

    private OrderDto mapOrderDto(Order order) {
        Menu menu = order.getMenu();
        int count = order.getCount();

        return new OrderDto(menu.getName(), count);
    }
}
