package christmas.service.order;

import christmas.domain.order.OrderValidator;
import christmas.domain.order.Orders;
import christmas.dto.OrdersDto;

public class OrderService {
    private final OrderValidator orderValidator;

    public OrderService(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public Orders placeOrder(OrderMapper orderMapper, OrdersDto ordersDto) {
        Orders orders = orderMapper.toEntity(ordersDto);
        orders.validate(orderValidator);

        return orders;
    }
}
