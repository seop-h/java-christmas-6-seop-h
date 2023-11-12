package christmas.domain.model.order;

import christmas.domain.model.order.menu.Menu;
import christmas.domain.model.order.menu.Serving;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @ParameterizedTest
    @MethodSource("orderAndAmount")
    @DisplayName("주문에 대한 음식당 총금액을 계산한다.")
    void calculateOrderAmountSuccessfully(Menu menu, Serving serving, int expected) {
        Order order = new Order(menu, serving);
        assertThat(order.calculateOrderAmount()).isEqualTo(expected);
    }

    private static Stream<Arguments> orderAndAmount() {
        return Stream.of(
                Arguments.of(Menu.MUSHROOM_SOUP, new Serving(1), 6_000),
                Arguments.of(Menu.TAPAS, new Serving(3), 16_500),
                Arguments.of(Menu.CAESAR_SALAD, new Serving(2), 16_000),

                Arguments.of(Menu.T_BONE_STEAK, new Serving(2), 110_000),
                Arguments.of(Menu.BARBECUE_RIBS, new Serving(10), 540_000),
                Arguments.of(Menu.SEAFOOD_PASTA, new Serving(7), 245_000),
                Arguments.of(Menu.CHRISTMAS_PASTA, new Serving(4), 100_000),

                Arguments.of(Menu.CHOCOLATE_CAKE, new Serving(5), 75_000),
                Arguments.of(Menu.ICE_CREAM, new Serving(11), 55_000),

                Arguments.of(Menu.ZERO_COLA, new Serving(20), 60_000),
                Arguments.of(Menu.RED_WINE, new Serving(2), 120_000),
                Arguments.of(Menu.CHAMPAGNE, new Serving(3), 75_000)
        );
    }

}