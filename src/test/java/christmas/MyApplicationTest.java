package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

public class MyApplicationTest extends NsTest {

    @Test
    @DisplayName("잘못된 날짜를 입력받는 경우 예외 메시지를 발생시킨다.")
    void inputIncorrectDate() {
        assertSimpleTest(() -> {
            run("a", "asdf", //숫자가 아닌 경우
                    "0", "32", "-1", "100", //1~31 사이의 숫자가 아닌 경우
                    "5", "티본스테이크-1");
            assertThat(countErrorMessage(output(),
                    "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
            ).isEqualTo(6);
        });
    }

    private long countErrorMessage(String text, String target) {
        return IntStream.range(0, text.length() - target.length() + 1)
                .filter(i -> text.substring(i, i + target.length()).equals(target))
                .count();
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
