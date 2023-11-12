package christmas.domain.model.order.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    @ParameterizedTest
    @MethodSource("menuNameAndMatchingMenu")
    @DisplayName("메뉴 이름을 받으면 그에 맞는 메뉴를 반환한다")
    void findMatchMenuSuccessfully(String menuName, Menu expected) {
        Menu matchMenu = Menu.findMatch(menuName);
        assertThat(matchMenu).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdf", "", "양송이 수프", " 타파스", "시저샐러드 "})
    @NullSource
    @DisplayName("메뉴판에 없는 메뉴 이름을 받으면 null을 반환한다")
    void findNoMatchMenu(String menuName) {
        Menu matchMenu = Menu.findMatch(menuName);
        assertThat(matchMenu).isNull();
    }

    @ParameterizedTest
    @MethodSource("menuAndMatchingType")
    @DisplayName("모든 메뉴는 적잘한 타입(에피타이저, 메인 등)을 가진다")
    void isKindOfTrueTest(Menu menu, Type type) {
        assertThat(menu.isKindOf(type)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("menuAndIncorrectType")
    @DisplayName("메뉴와 타입이 일치하지 않으면 false를 반환한다.")
    void isKindOfFalseTest(Menu menu, List<Type> types) {
        for (Type type : types) {
            assertThat(menu.isKindOf(type)).isFalse();
        }
    }

    private static Stream<Arguments> menuNameAndMatchingMenu() {
        return Stream.of(
                Arguments.of("양송이수프", Menu.MUSHROOM_SOUP),
                Arguments.of("타파스", Menu.TAPAS),
                Arguments.of("시저샐러드", Menu.CAESAR_SALAD),

                Arguments.of("티본스테이크", Menu.T_BONE_STEAK),
                Arguments.of("바비큐립", Menu.BARBECUE_RIBS),
                Arguments.of("해산물파스타", Menu.SEAFOOD_PASTA),
                Arguments.of("크리스마스파스타", Menu.CHRISTMAS_PASTA),

                Arguments.of("초코케이크", Menu.CHOCOLATE_CAKE),
                Arguments.of("아이스크림", Menu.ICE_CREAM),

                Arguments.of("제로콜라", Menu.ZERO_COLA),
                Arguments.of("레드와인", Menu.RED_WINE),
                Arguments.of("샴페인", Menu.CHAMPAGNE)
        );
    }

    private static Stream<Arguments> menuAndMatchingType() {
        return Stream.of(
                Arguments.of(Menu.MUSHROOM_SOUP, Type.APPETIZER),
                Arguments.of(Menu.TAPAS, Type.APPETIZER),
                Arguments.of(Menu.CAESAR_SALAD, Type.APPETIZER),

                Arguments.of(Menu.T_BONE_STEAK, Type.MAIN),
                Arguments.of(Menu.BARBECUE_RIBS, Type.MAIN),
                Arguments.of(Menu.SEAFOOD_PASTA, Type.MAIN),
                Arguments.of(Menu.CHRISTMAS_PASTA, Type.MAIN),

                Arguments.of(Menu.CHOCOLATE_CAKE, Type.DESSERT),
                Arguments.of(Menu.ICE_CREAM, Type.DESSERT),

                Arguments.of(Menu.ZERO_COLA, Type.BEVERAGE),
                Arguments.of(Menu.RED_WINE, Type.BEVERAGE),
                Arguments.of(Menu.CHAMPAGNE, Type.BEVERAGE)
        );
    }

    private static Stream<Arguments> menuAndIncorrectType() {
        return Stream.of(
                Arguments.of(Menu.MUSHROOM_SOUP, List.of(Type.MAIN, Type.DESSERT, Type.BEVERAGE)),
                Arguments.of(Menu.TAPAS, List.of(Type.MAIN, Type.DESSERT, Type.BEVERAGE)),
                Arguments.of(Menu.CAESAR_SALAD, List.of(Type.MAIN, Type.DESSERT, Type.BEVERAGE)),

                Arguments.of(Menu.T_BONE_STEAK, List.of(Type.APPETIZER, Type.DESSERT, Type.BEVERAGE)),
                Arguments.of(Menu.BARBECUE_RIBS, List.of(Type.APPETIZER, Type.DESSERT, Type.BEVERAGE)),
                Arguments.of(Menu.SEAFOOD_PASTA, List.of(Type.APPETIZER, Type.DESSERT, Type.BEVERAGE)),
                Arguments.of(Menu.CHRISTMAS_PASTA, List.of(Type.APPETIZER, Type.DESSERT, Type.BEVERAGE)),

                Arguments.of(Menu.CHOCOLATE_CAKE, List.of(Type.APPETIZER, Type.MAIN, Type.BEVERAGE)),
                Arguments.of(Menu.ICE_CREAM, List.of(Type.APPETIZER, Type.MAIN, Type.BEVERAGE)),

                Arguments.of(Menu.ZERO_COLA, List.of(Type.APPETIZER, Type.MAIN, Type.DESSERT)),
                Arguments.of(Menu.RED_WINE, List.of(Type.APPETIZER, Type.MAIN, Type.DESSERT)),
                Arguments.of(Menu.CHAMPAGNE, List.of(Type.APPETIZER, Type.MAIN, Type.DESSERT))
        );
    }

}