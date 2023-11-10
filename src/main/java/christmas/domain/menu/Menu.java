package christmas.domain.menu;

public interface Menu {

    default boolean isDrink() {
        return false;
    }
}
