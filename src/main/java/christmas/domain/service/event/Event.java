package christmas.domain.service.event;

import christmas.domain.model.order.menu.Menu;

import java.util.Map;

import static christmas.domain.service.event.EventAmountConst.D_DAY_INCREMENT;
import static christmas.domain.service.event.EventAmountConst.D_DAY_START;
import static christmas.domain.service.event.EventAmountConst.GIVEAWAY_PRODUCT;
import static christmas.domain.service.event.EventAmountConst.PER_MENU;
import static christmas.domain.service.event.EventAmountConst.SPECIAL_AMOUNT;

/**
 * 이벤트 내용을 저장하고 있는 enum 클래스다.<br>
 * 각 이벤트마다 적용 방식이 상이해서 객체별 유효한 필드가 다르다.
 *
 * <p>1. CHRISTMAS_D_DAY: 첫날 할인 금액 1000원, 증분(공차) 100원<br>
 * 2. WEEKDAY, WEEKEND: 특정 메뉴당 2023원 할인<br>
 * 3. SPECIAL: 1000원 할인<br>
 * 4. GIVEAWAY: 증정 상품 샴페인 1개
 */
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
