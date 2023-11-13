package christmas.domain.menu;

import christmas.domain.order.Count;

public enum Drink implements Menu {
    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean isDrink() {
        return true;
    }

    @Override
    public boolean isMatchName(String name) {
        return this.name.equals(name);
    }

    @Override
    public int calculateOrderAmount(Count count) {
        return count.multiplyPrice(price);
    }

    @Override
    public String getName() {
        return name;
    }
}
