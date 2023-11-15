package christmas.domain.model.event;

import static christmas.domain.model.event.EventAmountConst.D_DAY_INCREMENT;
import static christmas.domain.model.event.EventAmountConst.D_DAY_START;
import static christmas.domain.model.event.EventAmountConst.GIVEAWAY_PRODUCT;
import static christmas.domain.model.event.EventAmountConst.PER_MENU;
import static christmas.domain.model.event.EventAmountConst.SPECIAL_AMOUNT;

public enum Event {

    CHRISTMAS_D_DAY("크리스마스 디데이 할인") {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return D_DAY_START + (consideredValue - 1) * D_DAY_INCREMENT;
        }
    },

    WEEKDAY("평일 할인") {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return consideredValue * PER_MENU;
        }
    },

    WEEKEND("주말 할인") {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return consideredValue * PER_MENU;
        }
    },

    SPECIAL("특별 할인") {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return SPECIAL_AMOUNT;
        }
    },

    GIVEAWAY("증정 이벤트") {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return GIVEAWAY_PRODUCT.calculateOrderAmount();
        }
    };

    private final String description;

    Event(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract int calculateBenefitAmount(Integer consideredValue);

}
