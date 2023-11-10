package christmas.domain.menu;

public interface Menu {

    default boolean isDrink() {
        return false;
    }

    boolean isMatchName(String name);

    int getPrice();
}
