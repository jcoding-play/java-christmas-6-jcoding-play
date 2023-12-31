package christmas.domain.menu;

public enum Appetizer implements Menu {
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000);

    private final String name;
    private final int price;

    Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean isMatchName(String name) {
        return this.name.equals(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
