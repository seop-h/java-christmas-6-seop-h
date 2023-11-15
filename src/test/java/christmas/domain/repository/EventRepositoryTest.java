package christmas.domain.repository;

import christmas.domain.model.event.Event;
import christmas.domain.model.event.EventDetail;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static christmas.domain.model.event.Event.CHRISTMAS_D_DAY;
import static christmas.domain.model.event.Event.GIVEAWAY;
import static christmas.domain.model.event.Event.SPECIAL;
import static christmas.domain.model.event.Event.WEEKDAY;
import static christmas.domain.model.event.Event.WEEKEND;

class EventRepositoryTest {

    @ParameterizedTest
    @MethodSource("makeReservationIncorrectly")
    @DisplayName("이벤트 상세 내역에서 찾고자 하는 이벤트 종류가 있는지 확인한다.")
    void checkEventRepositoryContainsWorkProperly(Event event, boolean expected) {
        EventRepository eventRepository = makeRepository();

        Assertions.assertThat(eventRepository.contains(event)).isEqualTo(expected);
    }

    private EventRepository makeRepository() {
        EventRepository eventRepository = new EventRepository();

        eventRepository.add(new EventDetail(CHRISTMAS_D_DAY,1_500));
        eventRepository.add(new EventDetail(WEEKEND,2_023));
        eventRepository.add(new EventDetail(SPECIAL,1_000));

        return eventRepository;
    }

    private static Stream<Arguments> makeReservationIncorrectly() {

        return Stream.of(
                Arguments.of(CHRISTMAS_D_DAY, true),
                Arguments.of(WEEKEND, true),
                Arguments.of(WEEKDAY, false),
                Arguments.of(SPECIAL, true),
                Arguments.of(GIVEAWAY, false)
        );
    }

}