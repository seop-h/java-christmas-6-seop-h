package christmas.ui.output;

public interface OutputMessage {

    String INTRODUCE_PROMOTION = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    String ORDER_MENU = "<주문 메뉴>";
    String TOTAL_AMOUNT_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    String TOTAL_AMOUNT_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";

    String GIVEAWAY_MENU = "<증정 메뉴>";
    String EVENT_HISTORY = "<혜택 내역>";
    String TOTAL_BENEFIT_AMOUNT = "<총혜택 금액>";
    String EVENT_BADGE = "<12월 이벤트 배지>";

    String NOTHING = "없음";

    String UNIT = "원";

    static String makeEventOverview(int date) {
        return "12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    }

    static String makeTotalBenefitAmount(String discountAmount) {
        if (discountAmount.equals("0")) {
            return discountAmount + UNIT;
        }
        return "-" + discountAmount + UNIT;
    }

}
