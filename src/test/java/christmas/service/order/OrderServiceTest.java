package christmas.service.order;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderValidator;
import christmas.domain.order.Orders;
import christmas.dto.OrderDto;
import christmas.dto.OrdersDto;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderServiceTest {
    private OrderService orderService;
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        OrderValidator orderValidator = new OrderValidator();
        orderService = new OrderService(orderValidator);
        orderMapper = new OrderMapper(new MenuRepository());
    }

    @Test
    @DisplayName("주문을 할 수 있다.")
    void placeOrder() {
        List<OrderDto> orders = List.of(new OrderDto("타파스", 1), new OrderDto("제로콜라", 1));
        OrdersDto ordersDto = new OrdersDto(orders);

        Orders order = orderService.placeOrder(orderMapper, ordersDto);

        assertThat(order).extracting("orders", InstanceOfAssertFactories.list(Order.class))
                .containsExactly(new Order(Appetizer.TAPAS, 1), new Order(Drink.ZERO_COLA, 1));
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴가 입력된 경우 예외가 발생한다.")
    void invalidPlaceOrder() {
        OrdersDto ordersDto = new OrdersDto(List.of(new OrderDto("먹다남은짬뽕밥", 3)));

        assertThatThrownBy(() -> orderService.placeOrder(orderMapper, ordersDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}