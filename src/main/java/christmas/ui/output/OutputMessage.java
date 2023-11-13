package christmas.ui.output;

public interface OutputMessage {

    String INTRODUCE_PROMOTION = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    String ORDER_MENU = "<주문 메뉴>";

    static String makeEventOverview(int date) {
        return "12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    }

    static String makeMenuDetail(String menuName, int serving) {
        return menuName + " " + serving + "개";
    }

}
