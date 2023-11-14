package christmas.utils;

import christmas.dto.OrderDto;
import christmas.dto.OrdersDto;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Converter {
    private static final String ORDER_MENUS_DELIMITER = ",";
    private static final String MENU_AND_COUNT_DELIMITER = "-";

    public static OrdersDto toOrdersDto(String menuAndCounts) {
        return Arrays.stream(menuAndCounts.split(ORDER_MENUS_DELIMITER))
                .map(Converter::generateOrderDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), OrdersDto::new));
    }

    private static OrderDto generateOrderDto(String menuAndCount) {
        String[] input = menuAndCount.split(MENU_AND_COUNT_DELIMITER);
        String name = input[0];
        int count = Integer.parseInt(input[1]);

        return new OrderDto(name, count);
    }
}
