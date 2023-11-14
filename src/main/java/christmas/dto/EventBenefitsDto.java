package christmas.dto;

import christmas.domain.benefit.EventBenefits;
import christmas.domain.event.Event;

import java.util.Map;

import static java.util.stream.Collectors.*;

public record EventBenefitsDto(Map<String, Integer> benefitDetails) {

    public static EventBenefitsDto from(EventBenefits eventBenefits) {
        Map<Event, Integer> benefitDetails = eventBenefits.getBenefitDetails();

        return benefitDetails.keySet()
                .stream()
                .collect(collectingAndThen(toMap(Event::getName, benefitDetails::get), EventBenefitsDto::new));
    }
}
