package christmas.domain.event;

import christmas.domain.menu.Menu;
import christmas.utils.Constants;

public class GiftMenu {
    private final Menu menu;
    private final int count;

    public GiftMenu(Menu menu, int count) {
        validateGiftMenu(menu, count);
        this.menu = menu;
        this.count = count;
    }

    private void validateGiftMenu(Menu menu, int count) {
        if (menu == null) {
            throw new IllegalArgumentException("존재하지 않는 메뉴입니다.");
        }
        if (count < Constants.MINIMUM_COUNT) {
            throw new IllegalArgumentException(
                    String.format("증정 메뉴에 대한 개수는 %d이상이어야 합니다.", Constants.MINIMUM_COUNT));
        }
    }

    public int calculateTotalPrice() {
        return menu.getPrice() * count;
    }

    public String getName() {
        return menu.getName();
    }

    public int getCount() {
        return count;
    }
}
