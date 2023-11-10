package christmas.service.order.dto;

import java.util.List;

public record OrderDto(List<OrderMenuDto> orderMenus) {
}
