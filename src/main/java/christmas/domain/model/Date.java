package christmas.domain.model;

import christmas.domain.constant.DateConst;

import static christmas.domain.Validator.checkCondition;
import static christmas.domain.constant.DateConst.FIRST_DAY;
import static christmas.domain.constant.DateConst.LAST_DAY;
import static christmas.domain.constant.DateConst.SPECIAL_DAYS;
import static christmas.domain.constant.DateConst.WEEKENDS;
import static christmas.domain.constant.ErrorMessage.NOT_INVALID_DATE;

public class Date {

    private final int value;

    public Date(int value) {
        validate(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isWeekend() {
        return (WEEKENDS.contains(value));
    }

    public boolean isSpecialDay() {
        return (SPECIAL_DAYS.contains(value));
    }

    private void validate(int value) {
        checkCondition(value < FIRST_DAY || value > LAST_DAY,
                NOT_INVALID_DATE);
    }
}
