package christmas.domain.service;

public enum Event {

    //TODO Event와 EventAmountConst를 결합?
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    GIVEAWAY("증정 이벤트");

    private final String description;

    Event(String description) {
        this.description = description;
    }

}
