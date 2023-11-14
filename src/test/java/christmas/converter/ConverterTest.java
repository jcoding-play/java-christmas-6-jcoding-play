package christmas.converter;

import christmas.dto.OrderDto;
import christmas.dto.OrderMenuDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ConverterTest {

    @Test
    @DisplayName("입력 형식에 맞게 입력된 메뉴와 개수를 통해 주문 메뉴를 알 수 있다.")
    void toOrderDto() {
        OrderDto orderDto = Converter.toOrderDto("타파스-1,제로콜라-1");
        List<OrderMenuDto> orderMenus = orderDto.orderMenus();

        assertThat(orderMenus).containsExactly(
                new OrderMenuDto("타파스", 1), new OrderMenuDto("제로콜라", 1));
    }
}