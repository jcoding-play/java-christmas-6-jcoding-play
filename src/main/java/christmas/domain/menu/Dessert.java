package christmas.domain.menu;

import christmas.domain.order.Count;

public enum Dessert implements Menu {
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);

    private final String name;
    private final int price;

    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean isDessert() {
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
