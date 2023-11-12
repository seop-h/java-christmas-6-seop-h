package christmas.domain.model.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static christmas.constant.ErrorMessage.NOT_INVALID_DATE;
import static christmas.domain.model.date.DateConst.FIRST_DAY;
import static christmas.domain.model.date.DateConst.LAST_DAY;
import static christmas.domain.model.date.DateConst.SPECIAL_DAYS;
import static christmas.domain.model.date.DateConst.WEEKENDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DateTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 32, -1, Integer.MAX_VALUE, Integer.MIN_VALUE})
    @DisplayName("날짜가 1~31 사이가 아니면 예외가 발생한다.")
    void createDateByIncorrectValue(int input) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Date(input)
        ).withMessage(NOT_INVALID_DATE);
    }

    @ParameterizedTest
    @MethodSource("weekendOrNot")
    @DisplayName("날짜에 따라 주말인지 평일인지 판단한다.")
    void createWeekendDate(List<Integer> dates, boolean expected) {
        for (Integer input : dates) {
            Date date = new Date(input);
            assertThat(date.isWeekend()).isEqualTo(expected);
        }
    }

    @ParameterizedTest
    @MethodSource("specialDayOrNot")
    @DisplayName("날짜에 따라 특별 할인을 받을 수 있는 날인지 아닌지 판단한다.")
    void createSpecialDate(List<Integer> dates, boolean expected) {
        for (Integer input : dates) {
            Date date = new Date(input);
            assertThat(date.isSpecialDay()).isEqualTo(expected);
        }
    }

    private static Stream<Arguments> weekendOrNot() {

        List<Integer> weekdays = makeOthers(WEEKENDS);

        return Stream.of(
                Arguments.of(WEEKENDS, true), //주말
                Arguments.of(weekdays, false) //평일
        );
    }

    private static Stream<Arguments> specialDayOrNot() {

        List<Integer> normalDays = makeOthers(SPECIAL_DAYS);

        return Stream.of(
                Arguments.of(SPECIAL_DAYS, true), //특별한 날(별이 있는 날)
                Arguments.of(normalDays, false) //평범한 날
        );
    }

    private static List<Integer> makeOthers(List<Integer> dates) {
        List<Integer> december = IntStream.range(FIRST_DAY, LAST_DAY + 1)
                .boxed()
                .collect(Collectors.toList());
        december.removeAll(dates);

        return december;
    }

}