package christmas.domain.menu;

public interface Menu {

    default boolean isDrink() {
        return false;
    }

    default boolean isDessert() {
        return false;
    }

    default boolean isMain() {
        return false;
    }

    boolean isMatchName(String name);

    String getName();

    int getPrice();
}
