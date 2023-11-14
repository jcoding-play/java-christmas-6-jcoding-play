package christmas.converter;

import christmas.dto.OrderDto;
import christmas.dto.OrderMenuDto;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Converter {
    private static final String ORDER_MENUS_DELIMITER = ",";
    private static final String MENU_AND_COUNT_DELIMITER = "-";

    public static OrderDto toOrderDto(String menuAndCounts) {
        return Arrays.stream(menuAndCounts.split(ORDER_MENUS_DELIMITER))
                .map(Converter::generateOrderMenuDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), OrderDto::new));
    }

    private static OrderMenuDto generateOrderMenuDto(String menuAndCount) {
        String[] input = menuAndCount.split(MENU_AND_COUNT_DELIMITER);
        return new OrderMenuDto(input[0], Integer.parseInt(input[1]));
    }
}
