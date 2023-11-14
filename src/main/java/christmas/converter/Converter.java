package christmas.converter;

import christmas.domain.event.Event;
import christmas.dto.OrdersDto;
import christmas.dto.OrderDto;

import java.util.Arrays;
import java.util.Map;
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

    public static Map<String, Integer> toString(Map<Event, Integer> benefitDetails) {
        return benefitDetails.keySet()
                .stream()
                .collect(Collectors.toMap(
                        Event::getName,
                        benefitDetails::get));
    }
}
