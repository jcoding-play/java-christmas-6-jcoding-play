package christmas.domain.benefit;

import java.util.Arrays;
import java.util.function.Predicate;

public enum EventBadge {
    SANTA("산타", totalBenefitAmount -> totalBenefitAmount >= 20000),
    TREE("트리", totalBenefitAmount -> totalBenefitAmount >= 10000 && totalBenefitAmount < 20000),
    STAR("별", totalBenefitAmount -> totalBenefitAmount >= 5000 && totalBenefitAmount < 10000),
    NOTHING("없음", totalBenefitAmount -> totalBenefitAmount < 5000);

    private final String name;
    private final Predicate<Integer> predicate;

    EventBadge(String name, Predicate<Integer> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public static EventBadge of(int totalBenefitAmount) {
        return Arrays.stream(values())
                .filter(eventBadge -> isMatch(eventBadge, totalBenefitAmount))
                .findFirst()
                .orElse(NOTHING);
    }

    private static boolean isMatch(EventBadge eventBadge, int totalBenefitAmount) {
        return eventBadge.predicate
                .test(totalBenefitAmount);
    }

    public String getName() {
        return name;
    }
}
