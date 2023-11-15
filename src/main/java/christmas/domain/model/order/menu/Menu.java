package christmas.domain.model.order.menu;

import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP(Type.APPETIZER, "양송이수프", 6_000),
    TAPAS(Type.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(Type.APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(Type.MAIN, "티본스테이크", 55_000),
    BARBECUE_RIBS(Type.MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(Type.MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(Type.MAIN, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(Type.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(Type.DESSERT, "아이스크림", 5_000),

    ZERO_COLA(Type.BEVERAGE, "제로콜라", 3_000),
    RED_WINE(Type.BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(Type.BEVERAGE, "샴페인", 25_000);

    private final Type type;
    private final String name;
    private final int price;

    Menu(Type type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static Menu findMatch(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_INVALID_ORDER));
    }

    public boolean isKindOf(Type expected) {
        return this.type.equals(expected);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
