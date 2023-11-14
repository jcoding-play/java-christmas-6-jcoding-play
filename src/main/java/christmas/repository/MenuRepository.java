package christmas.repository;

import christmas.domain.menu.*;

import java.util.HashMap;
import java.util.Map;

public class MenuRepository {
    private static Map<String, Menu> store = new HashMap<>();

    static {
        saveMenusOf(Appetizer.values());
        saveMenusOf(Main.values());
        saveMenusOf(Dessert.values());
        saveMenusOf(Drink.values());
    }

    private static void saveMenusOf(Menu[] menus) {
        for (Menu menu : menus) {
            store.put(menu.getName(), menu);
        }
    }

    public Menu findByName(String name) {
        return store.values()
                .stream()
                .filter(menu -> menu.isMatchName(name))
                .findFirst()
                .orElse(null);
    }
}
