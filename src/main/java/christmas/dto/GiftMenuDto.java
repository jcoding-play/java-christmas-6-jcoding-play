package christmas.dto;

import christmas.domain.event.GiftMenu;
import christmas.utils.Constants;

public record GiftMenuDto(String name, int count) {

    public static GiftMenuDto from(GiftMenu giftMenu) {
        if (giftMenu == null) {
            return new GiftMenuDto(null, Constants.NUMBER_OF_GIFT_EVENTS_NOT_APPLIED);
        }
        return new GiftMenuDto(giftMenu.getName(), giftMenu.getCount());
    }
}
