package christmas.dto;

import java.util.List;

public record OrderDto(List<OrderMenuDto> orderMenus) {
}
