package christmas.domain.model;

import christmas.constant.ErrorMessage;
import christmas.domain.model.order.menu.Type;
import christmas.domain.service.reservation.ReservationService;
import christmas.message.StackTrace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ReservationTest {

    private final ReservationService service = new ReservationService();

    @ParameterizedTest
    @MethodSource("makeReservationIncorrectly")
    @DisplayName("주문 메뉴의 총 개수가 20개를 넘거나 음료밖에 없으면 예외가 발생한다.")
    void createReservationMoreThan20(Map<String, Integer> orderInput) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                        service.makeReservation(1, orderInput)
                ).withMessage(ErrorMessage.NOT_INVALID_ORDER)
                .withStackTraceContaining(StackTrace.RESERVATION_VALIDATE);
    }

    @ParameterizedTest
    @MethodSource("ordersAndEachServingCount")
    @DisplayName("특정 타입의 메뉴 개수가 기대하는 값과 일치하는지 검사한다.")
    void checkCountOfSpecificType(Map<String, Integer> orderInput, int appetizerCount, int mainCount, int dessertCount, int beverageCount) {
        Reservation reservation = service.makeReservation(5, orderInput);

        assertThat(reservation.calculateDishesOf(Type.APPETIZER)).isEqualTo(appetizerCount);
        assertThat(reservation.calculateDishesOf(Type.MAIN)).isEqualTo(mainCount);
        assertThat(reservation.calculateDishesOf(Type.DESSERT)).isEqualTo(dessertCount);
        assertThat(reservation.calculateDishesOf(Type.BEVERAGE)).isEqualTo(beverageCount);
    }

    @ParameterizedTest
    @MethodSource("ordersAndTotalOrderAmount")
    @DisplayName("총 주문 금액이 기대하는 값과 일치하는지 검사한다.")
    void checkTotalOrderAmount(Map<String, Integer> orderInput, int expected) {
        Reservation reservation = service.makeReservation(5, orderInput);

        assertThat(reservation.calculateTotalOrderAmount()).isEqualTo(expected);

    }

    private static Stream<Arguments> makeReservationIncorrectly() {

        return Stream.of(
                Arguments.of(Map.of("양송이수프", 1, "타파스", 3, "티본스테이크", 3, "초코케이크", 5, "제로콜라", 9)),
                Arguments.of(Map.of("초코케이크", 10, "아이스크림", 10, "제로콜라", 1)),
                Arguments.of(Map.of("해산물파스타", 1, "레드와인", 20)),

                Arguments.of(Map.of("제로콜라", 1, "레드와인", 1, "샴페인", 1)),
                Arguments.of(Map.of("제로콜라", 1, "레드와인", 1)),
                Arguments.of(Map.of("레드와인", 1, "샴페인", 1)),
                Arguments.of(Map.of("제로콜라", 1, "샴페인", 1)),
                Arguments.of(Map.of("제로콜라", 20))
        );
    }

    private static Stream<Arguments> ordersAndEachServingCount() {
        List<Map<String, Integer>> orderInputs = makeOrderInputs();

        /**
         * 순서대로 주문 목록, 에피타이저 개수, 메인 개수, 디저트 개수, 음료 개수
         */
        return Stream.of(
                Arguments.of(orderInputs.get(0), 4, 7, 5, 4),
                Arguments.of(orderInputs.get(1), 3, 1, 4, 2),
                Arguments.of(orderInputs.get(2), 5, 0, 4, 0),
                Arguments.of(orderInputs.get(3), 0, 3, 0, 0)
        );
    }

    private static Stream<Arguments> ordersAndTotalOrderAmount() {
        List<Map<String, Integer>> orderInputs = makeOrderInputs();

        return Stream.of(
                Arguments.of(orderInputs.get(0), 550_000),
                Arguments.of(orderInputs.get(1), 143_000),
                Arguments.of(orderInputs.get(2), 50_000),
                Arguments.of(orderInputs.get(3), 162_000)
        );
    }

    private static List<Map<String, Integer>> makeOrderInputs() {
        Map<String, Integer> orderInput1 = Map.ofEntries(
                Map.entry("양송이수프", 1),
                Map.entry("타파스", 2),
                Map.entry("시저샐러드", 1),
                Map.entry("티본스테이크", 2),
                Map.entry("바비큐립", 3),
                Map.entry("해산물파스타", 1),
                Map.entry("크리스마스파스타", 1),
                Map.entry("초코케이크", 2),
                Map.entry("아이스크림", 3),
                Map.entry("제로콜라", 1),
                Map.entry("레드와인", 2),
                Map.entry("샴페인", 1)
        );

        Map<String, Integer> orderInput2 = Map.ofEntries(
                Map.entry("양송이수프", 3),
                Map.entry("티본스테이크", 1),
                Map.entry("아이스크림", 4),
                Map.entry("샴페인", 2)
        );

        Map<String, Integer> orderInput3 = Map.ofEntries(
                Map.entry("양송이수프", 5),
                Map.entry("아이스크림", 4)
        );

        Map<String, Integer> orderInput4 = Map.ofEntries(
                Map.entry("바비큐립", 3)
        );

        return List.of(orderInput1, orderInput2, orderInput3, orderInput4);
    }


}