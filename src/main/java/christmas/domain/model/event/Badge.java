package christmas.domain.model.event;

public enum Badge {

    NOTHING("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minimumAmount;

    Badge(String name, int minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public boolean isAmountSatisfied(int amount) {
        return (amount >= this.minimumAmount);
    }

    @Override
    public String toString() {
        return name;
    }
}
