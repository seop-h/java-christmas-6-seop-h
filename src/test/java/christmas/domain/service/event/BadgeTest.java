package christmas.domain.service.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {

    @ParameterizedTest
    @MethodSource("amountAndBadge")
    @DisplayName("총 혜택 금액이 뱃지 부여 최소 금액을 넘어서는지 판단한다.")
    void checkMinimumAmountToBeBadged(int totalBenefitAmount, Badge badge, boolean expected) {
        assertThat(badge.isAmountSatisfied(totalBenefitAmount)).isEqualTo(expected);
    }

    private static Stream<Arguments> amountAndBadge() {
        return Stream.of(
                Arguments.of(25_000, Badge.SANTA, true),
                Arguments.of(20_000, Badge.SANTA, true),
                Arguments.of(15_000, Badge.TREE, true),
                Arguments.of(10_000, Badge.TREE, true),
                Arguments.of(7_000, Badge.STAR, true),
                Arguments.of(5_000, Badge.STAR, true),

                Arguments.of(19_999, Badge.SANTA, false),
                Arguments.of(15_000, Badge.SANTA, false),
                Arguments.of(9_999, Badge.TREE, false),
                Arguments.of(6_000, Badge.TREE, false),
                Arguments.of(4_999, Badge.STAR, false),
                Arguments.of(3_000, Badge.STAR, false)
        );
    }

}