package christmas.converter;

import christmas.dto.OrdersDto;
import christmas.dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ConverterTest {

    @Test
    @DisplayName("입력 형식에 맞게 입력된 메뉴와 개수를 통해 주문 메뉴를 알 수 있다.")
    void toOrderDto() {
        OrdersDto ordersDto = Converter.toOrdersDto("타파스-1,제로콜라-1");
        List<OrderDto> orders = ordersDto.orders();

        assertThat(orders).containsExactly(
                new OrderDto("타파스", 1), new OrderDto("제로콜라", 1));
    }
}