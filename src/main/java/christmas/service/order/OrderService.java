package christmas.service.order;

import christmas.domain.order.Order;
import christmas.domain.order.OrderValidator;
import christmas.dto.OrderDto;

public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;

    public OrderService(OrderMapper orderMapper, OrderValidator orderValidator) {
        this.orderMapper = orderMapper;
        this.orderValidator = orderValidator;
    }

    public Order placeOrder(OrderDto orderDto) {
        Order order = orderMapper.mapFrom(orderDto);
        order.validate(orderValidator);

        return order;
    }
}
