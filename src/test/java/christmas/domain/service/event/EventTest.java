package christmas.domain.service.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.domain.model.event.Event.CHRISTMAS_D_DAY;
import static christmas.domain.model.event.Event.GIVEAWAY;
import static christmas.domain.model.event.Event.SPECIAL;
import static christmas.domain.model.event.Event.WEEKDAY;
import static christmas.domain.model.event.Event.WEEKEND;
import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @ParameterizedTest
    @CsvSource(value = {"1:1000", "2:1100", "3:1200", "24:3300", "25:3400"}, delimiter = ':')
    @DisplayName("크리스마스 디데이 할인금액 계산이 정상적으로 적용되는지 확인한다.")
    void checkChristmasDDayDiscount(Integer date, Integer expected) {
        assertThat(CHRISTMAS_D_DAY.calculateBenefitAmount(date)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:2023", "2:4046", "3:6069", "20:40460"}, delimiter = ':')
    @DisplayName("평일 할인금액 계산이 정상적으로 적용되는지 확인한다.")
    void checkWeekdayDiscount(Integer servings, Integer expected) {
        assertThat(WEEKDAY.calculateBenefitAmount(servings)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:2023", "2:4046", "3:6069", "20:40460"}, delimiter = ':')
    @DisplayName("주말 할인금액 계산이 정상적으로 적용되는지 확인한다.")
    void checkWeekendDiscount(Integer servings, Integer expected) {
        assertThat(WEEKEND.calculateBenefitAmount(servings)).isEqualTo(expected);
    }

    @Test
    @DisplayName("특별 할인금액 계산이 정상적으로 적용되는지 확인한다.")
    void checkSpecialDiscount() {
        assertThat(SPECIAL.calculateBenefitAmount(null)).isEqualTo(1_000);
    }

    @Test
    @DisplayName("증정 이벤트의 값어치 계산이 정상적으로 적용되는지 확인한다.")
    void checkGiveawayWorth() {
        assertThat(GIVEAWAY.calculateBenefitAmount(null)).isEqualTo(25_000);
    }

}