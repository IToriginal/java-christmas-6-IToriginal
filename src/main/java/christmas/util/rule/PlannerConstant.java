package christmas.util.rule;

public enum PlannerConstant {

    FREEBIES_TARGET(120000),
    YEAR(2023),
    DECEMBER(12),
    START_DATE(1),
    END_DATE(31),
    CRISTMAS_DATE(25),
    DAILY_INCREASE(100),
    ZERO_VALUE(0),
    DEFAULT_DISCOUNT_AMOUNT(1000),
    WEEK_DISCOUNT_AMOUNT(2023),
    CHRISTMAS_DATE(25),
    SPECAIL_DISCOUNT(1000);

    private final Integer value;

    PlannerConstant(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
