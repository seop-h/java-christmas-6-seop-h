package christmas.domain.model.order.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class ServingTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 21, Integer.MAX_VALUE, Integer.MIN_VALUE})
    @DisplayName("메뉴의 개수가 1보다 작거나 20보다 크면 예외가 발생한다")
    void createServingFail(int value) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Serving(value)
        ).withMessage(NOT_INVALID_ORDER);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 19, 20})
    @DisplayName("메뉴의 개수가 1~20 사이면 정상적으로 객체를 생성한다.")
    void createServingSuccessfully(int value) {
        Serving serving = new Serving(value);
        assertThat(serving.getValue()).isEqualTo(value);
    }

}