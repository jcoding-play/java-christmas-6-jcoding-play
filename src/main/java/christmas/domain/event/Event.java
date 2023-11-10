package christmas.domain.event;

public interface Event {

    boolean isApplicable(int date, int totalOrderPrice);
}
