package christmas.domain.model;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minimumAmount;

    Badge(String name, int minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }
}
