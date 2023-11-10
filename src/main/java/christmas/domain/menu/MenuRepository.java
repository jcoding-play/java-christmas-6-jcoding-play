package christmas.domain.menu;

import java.util.*;

public class MenuRepository {
    private static Map<Type, List<Menu>> store = new EnumMap<>(Type.class);

    static {
        initializeMenusOf(Type.APPETIZER, Appetizer.values());
        initializeMenusOf(Type.MAIN, Main.values());
        initializeMenusOf(Type.DESSERT, Dessert.values());
        initializeMenusOf(Type.DRINK, Drink.values());
    }

    private static void initializeMenusOf(Type type, Menu[] values) {
        store.put(type, addMenuList(values));
    }

    private static List<Menu> addMenuList(Menu[] menus) {
        return Arrays.stream(menus)
                .toList();
    }

    public List<Menu> findByType(Type type) {
        return new ArrayList<>(store.get(type));
    }

    public Optional<Menu> findByName(String name) {
        return store.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(menu -> menu.isMatchName(name))
                .findFirst();
    }
}
