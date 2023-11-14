package christmas.domain.service.event;

import christmas.domain.model.Reservation;
import christmas.domain.service.reservation.ReservationMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static christmas.domain.service.event.Event.CHRISTMAS_D_DAY;
import static christmas.domain.service.event.Event.GIVEAWAY;
import static christmas.domain.service.event.Event.SPECIAL;
import static christmas.domain.service.event.Event.WEEKDAY;
import static christmas.domain.service.event.Event.WEEKEND;
import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest {

    @ParameterizedTest
    @MethodSource("dateAndOrdersAndEventDetails")
    @DisplayName("모든 이벤트가 정상적으로 적용되는지 판단한다.")
    void checkAllEventsCorrectlyApply(
            int dateInput,
            Map<String, Integer> orderInput,
            Map<Event, Integer> eventDetailsExpected,
            Badge badgeExpected
    ) {
        ReservationMaker reservationMaker = new ReservationMaker();
        Reservation reservation = reservationMaker.execute(dateInput, orderInput);

        EventService eventService = new EventService(reservation);
        eventService.applyEvent();

        assertThat(eventService)
                .extracting("eventDetails")
                .isEqualTo(eventDetailsExpected);
        assertThat(eventService.giveBadge()).isEqualTo(badgeExpected);
    }

    private static Stream<Arguments> dateAndOrdersAndEventDetails() {
        List<Map<String, Integer>> orderInputs = makeOrderInputs();
        List<Map<Event, Integer>> eventDetails = makeEventDetails();

        return Stream.of(
                Arguments.of(1, orderInputs.get(0), eventDetails.get(0), Badge.SANTA),
                Arguments.of(10, orderInputs.get(0), eventDetails.get(1), Badge.SANTA),
                Arguments.of(25, orderInputs.get(0), eventDetails.get(2), Badge.SANTA),

                Arguments.of(5, orderInputs.get(1), eventDetails.get(3), Badge.SANTA),
                Arguments.of(9, orderInputs.get(1), eventDetails.get(4), Badge.SANTA),
                Arguments.of(31, orderInputs.get(1), eventDetails.get(5), Badge.SANTA),

                Arguments.of(3, orderInputs.get(2), eventDetails.get(6), Badge.TREE),
                Arguments.of(15, orderInputs.get(2), eventDetails.get(7), Badge.NOTHING),
                Arguments.of(28, orderInputs.get(2), eventDetails.get(8), Badge.STAR),

                Arguments.of(12, orderInputs.get(3), eventDetails.get(9), Badge.NOTHING),
                Arguments.of(27, orderInputs.get(3), eventDetails.get(10), Badge.NOTHING),
                Arguments.of(29, orderInputs.get(3), eventDetails.get(11), Badge.NOTHING),

                Arguments.of(25, orderInputs.get(4), eventDetails.get(12), Badge.NOTHING)
        );
    }

    private static List<Map<String, Integer>> makeOrderInputs() {
        List<Map<String, Integer>> result = new ArrayList<>();

        result.add(Map.ofEntries(
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
        )); //총 주문금액 550,000 -> 이벤트 O

        result.add(Map.ofEntries(
                Map.entry("양송이수프", 3),
                Map.entry("티본스테이크", 1),
                Map.entry("아이스크림", 4),
                Map.entry("샴페인", 2)
        )); //총 주문금액 143,000 -> 이벤트 O

        result.add(Map.ofEntries(
                Map.entry("양송이수프", 5),
                Map.entry("아이스크림", 4)
        )); //총 주문금액 50,000 -> 이벤트 O


        result.add(Map.ofEntries(
                Map.entry("해산물파스타", 1)
        )); //총 주문금액 35,000 -> 이벤트 O

        result.add(Map.ofEntries(
                Map.entry("아이스크림", 1),
                Map.entry("제로콜라", 1)
        )); //총 주문금액 8,000 -> 이벤트 X

        return result;
    }

    private static List<Map<Event, Integer>> makeEventDetails() {
        List<Map<Event, Integer>> result = new ArrayList<>();

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 1_000),
                Map.entry(WEEKEND, 14_161),
                Map.entry(GIVEAWAY, 25_000)
        )); //1일, makeOrderInputs().get(0)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 1_900),
                Map.entry(WEEKDAY, 10_115),
                Map.entry(SPECIAL, 1_000),
                Map.entry(GIVEAWAY, 25_000)
        )); //10일, makeOrderInputs().get(0)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 3_400),
                Map.entry(WEEKDAY, 10_115),
                Map.entry(SPECIAL, 1_000),
                Map.entry(GIVEAWAY, 25_000)
        )); //25일, makeOrderInputs().get(0)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 1_400),
                Map.entry(WEEKDAY, 8_092),
                Map.entry(GIVEAWAY, 25_000)
        )); //5일, makeOrderInputs().get(1)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 1_800),
                Map.entry(WEEKEND, 2_023),
                Map.entry(GIVEAWAY, 25_000)
        )); //9일, makeOrderInputs().get(1)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(WEEKDAY, 8_092),
                Map.entry(SPECIAL, 1_000),
                Map.entry(GIVEAWAY, 25_000)
        )); //31일, makeOrderInputs().get(1)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 1_200),
                Map.entry(WEEKDAY, 8_092),
                Map.entry(SPECIAL, 1_000)
        )); //3일, makeOrderInputs().get(2)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 2_400)
        )); //15일, makeOrderInputs().get(2)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(WEEKDAY, 8_092)
        )); //28일, makeOrderInputs().get(2)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(CHRISTMAS_D_DAY, 2_100)
        )); //12일, makeOrderInputs().get(3)의 이벤트 적용 내역

        result.add(Map.ofEntries(
        )); //27일, makeOrderInputs().get(3)의 이벤트 적용 내역

        result.add(Map.ofEntries(
                Map.entry(WEEKEND, 2_023)
        )); //29일, makeOrderInputs().get(3)의 이벤트 적용 내역

        result.add(Map.ofEntries(
        )); //25일, makeOrderInputs().get(4)의 이벤트 적용 내역

        return result;
    }

}