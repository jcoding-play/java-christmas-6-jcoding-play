package christmas.service.order;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.service.order.dto.OrderDto;
import christmas.service.order.dto.OrderMenuDto;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    @Test
    @DisplayName("주문을 할 수 있다.")
    void placeOrder() {
        List<OrderMenuDto> orderMenus = List.of(new OrderMenuDto("타파스", 1), new OrderMenuDto("제로콜라", 1));
        OrderDto orderDto = new OrderDto(orderMenus);

        OrderService orderService = new OrderService(new MenuRepository());
        Order order = orderService.placeOrder(orderDto);

        assertThat(order).extracting("orderMenus", InstanceOfAssertFactories.list(OrderMenu.class))
                .containsExactly(
                        new OrderMenu(Optional.of(Appetizer.TAPAS), 1),
                        new OrderMenu(Optional.of(Drink.ZERO_COLA), 1));
    }
}