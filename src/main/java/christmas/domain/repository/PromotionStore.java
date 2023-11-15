package christmas.domain.repository;

import christmas.domain.model.Reservation;

public class PromotionStore {

    private final Reservation reservation;
    private final EventRepository eventRepository;

    private static PromotionStore promotionStore;

    private PromotionStore(Reservation reservation) {
        this.reservation = reservation;
        this.eventRepository = new EventRepository();
    }

    public static void initialize(Reservation reservation) {
        promotionStore = new PromotionStore(reservation);
    }

    public static Reservation getReservation() {
        return getInstance().reservation;
    }

    public static EventRepository getEventRepository() {
        return getInstance().eventRepository;
    }

    private static PromotionStore getInstance() {
        //TODO null 판단 부분 수정
        if (promotionStore == null) {
            throw new RuntimeException("PromotionStore가 정상적으로 초기화되지 않았습니다.");
        }
        return promotionStore;
    }
}
