package christmas.domain.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekEndDiscount implements Event {
    private final List<Integer> applicableDates;

    public WeekEndDiscount() {
        this.applicableDates = calculateApplicableDates();
    }

    private List<Integer> calculateApplicableDates() {
        List<Integer> applicableDates = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            for (int j = i; j <= 31; j = j + 7) {
                applicableDates.add(j);
            }
        }
        Collections.sort(applicableDates);
        return applicableDates;
    }
}
