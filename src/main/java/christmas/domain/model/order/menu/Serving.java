package christmas.domain.model.order.menu;

import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;
import static christmas.domain.Validator.checkCondition;
import static christmas.domain.model.order.OrderConst.MAXIMUM_TOTAL_DISH;
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
        checkCondition(value < MINIMUM_DISH || value > MAXIMUM_TOTAL_DISH,
                NOT_INVALID_ORDER);
    }
}
