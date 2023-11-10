package christmas.service.order;

import christmas.domain.order.Order;
import christmas.service.order.dto.OrderDto;

public class OrderService {
    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public Order placeOrder(OrderDto orderDto) {
        return orderMapper.mapFrom(orderDto);
    }
}
