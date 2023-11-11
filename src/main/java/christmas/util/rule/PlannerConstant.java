package christmas.util.rule;

public enum PlannerConstant {

    FREEBIES_TARGET(120000),
    YEAR(2023),
    DECEMBER(12),
    START_DATE(1),
    END_DATE(31),
    CRISTMAS_DATE(25),
    DAILY_INCREASE(100);

    private final Integer value;

    PlannerConstant(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
