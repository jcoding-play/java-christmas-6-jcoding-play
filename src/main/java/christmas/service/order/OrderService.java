package christmas.service.order;

import christmas.domain.order.Order;
import christmas.domain.order.OrderValidator;
import christmas.dto.OrderDto;

public class OrderService {
    private final OrderValidator orderValidator;

    public OrderService(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public Order placeOrder(OrderMapper orderMapper, OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order.validate(orderValidator);

        return order;
    }
}
