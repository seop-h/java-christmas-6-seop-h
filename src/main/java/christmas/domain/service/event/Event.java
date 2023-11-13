package christmas.domain.service.event;

import christmas.domain.model.order.menu.Menu;

import java.util.Map;

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

    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 1_000, 100) {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return CHRISTMAS_D_DAY.discountAmount + (consideredValue - 1) * CHRISTMAS_D_DAY.discountIncrement;
        }
    },

    WEEKDAY("평일 할인", 2_023) {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return consideredValue * WEEKDAY.discountAmount;
        }
    },

    WEEKEND("주말 할인", 2_023) {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return consideredValue * WEEKEND.discountAmount;
        }
    },

    SPECIAL("특별 할인", 1_000) {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return SPECIAL.discountAmount;
        }
    },

    GIVEAWAY("증정 이벤트", Map.of(Menu.CHAMPAGNE, 1)) {
        @Override
        public int calculateBenefitAmount(Integer consideredValue) {
            return GIVEAWAY.product.entrySet().stream()
                    .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                    .sum();
        }
    };

    private final String description;
    private final Integer discountAmount;
    private final Integer discountIncrement;
    private final Map<Menu, Integer> product;

    Event(String description, int discountAmount) {
        this.description = description;
        this.discountAmount = discountAmount;

        this.discountIncrement = null;
        this.product = null;
    }

    Event(String description, int discountAmount, int discountIncrement) {
        this.description = description;
        this.discountAmount = discountAmount;
        this.discountIncrement = discountIncrement;

        this.product = null;
    }

    Event(String description, Map<Menu,Integer> product) {
        this.description = description;
        this.product = product;

        this.discountAmount = null;
        this.discountIncrement = null;
    }

    public abstract int calculateBenefitAmount(Integer consideredValue);

}
