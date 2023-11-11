package christmas.domain.model.order.menu;

import christmas.domain.Validator;

import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;
import static christmas.domain.model.order.OrderConst.MINIMUM_DISH;

public class Serving {

    private final int value;

    public Serving(int value) {
        validate(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value) {
        Validator.checkCondition(value < MINIMUM_DISH,
                NOT_INVALID_ORDER);
    }
}
