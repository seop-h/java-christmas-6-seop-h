package christmas.domain.model.menu;

import christmas.domain.Validator;

import static christmas.domain.constant.ErrorMessage.NOT_INVALID_ORDER;
import static christmas.domain.constant.OrderConst.MINIMUM_DISH;

public class Dish {

    private final int value;

    public Dish(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        Validator.checkCondition(value < MINIMUM_DISH,
                NOT_INVALID_ORDER);
    }

}
