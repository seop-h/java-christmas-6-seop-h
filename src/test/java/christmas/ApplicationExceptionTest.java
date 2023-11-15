package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationExceptionTest extends NsTest {

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

    @Test
    @DisplayName("잘못된 주문메뉴를 입력받는 경우 예외 메시지를 발생시킨다.")
    void inputIncorrectOrders() {
        assertSimpleTest(() -> {
            run("5",
                    "ㅁㄴㅇㄹ", ",,,", "---", "ad-1-1", "a,b,c", //메뉴 형식이 틀린 경우
                    "aaa-3", "해산물 파스타-1", " 티본스테이크-5", "초코케이크 -3", //메뉴판에 없는 메뉴를 입력한 경우
                    "아이스크림-a", "양송이수프-0", //메뉴의 개수가 1 이상이 아닌 경우
                    "시저샐러드-1,시저샐러드-3", //중복 메뉴를 입력한 경우
                    "제로콜라-1", "샴페인-3,레드와인-1", //음료만 주문한 경우
                    "티본스테이크-21", "양송이수프-5,제로콜라-5,시저샐러드-5,타파스-6", //메뉴의 총 개수가 20보다 많은 경우
                    "타파스-2");
            assertThat(countErrorMessage(output(),
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."))
                    .isEqualTo(16);
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
