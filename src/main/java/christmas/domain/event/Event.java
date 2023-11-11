package christmas.domain.event;

import christmas.domain.VisitDate;

public interface Event {

    boolean isApplicable(VisitDate visitDate, int totalOrderPrice);
}
