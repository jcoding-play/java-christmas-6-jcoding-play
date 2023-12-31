package christmas.service.order;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.dto.OrderDto;
import christmas.dto.OrdersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class OrderMapperTest {
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        orderMapper = new OrderMapper(new MenuRepository());
    }

    @Test
    @DisplayName("OrderDto를 통해 Order를 얻을 수 있다.")
    void toEntity() {
        OrdersDto ordersDto = new OrdersDto(List.of(new OrderDto("타파스", 1), new OrderDto("제로콜라", 1)));

        Orders orders = orderMapper.toEntity(ordersDto);

        assertThat(orders).isEqualTo(new Orders(List.of(new Order(Appetizer.TAPAS, 1), new Order(Drink.ZERO_COLA, 1))));
    }

    @Test
    @DisplayName("Order를 통해 OrderDto를 얻을 수 있다.")
    void toDto() {
        Orders orders = new Orders(List.of(new Order(Appetizer.TAPAS, 1), new Order(Drink.ZERO_COLA, 1)));

        OrdersDto ordersDto = orderMapper.toDto(orders);

        assertThat(ordersDto).isEqualTo(new OrdersDto(List.of(new OrderDto("타파스", 1), new OrderDto("제로콜라", 1))));
    }
}