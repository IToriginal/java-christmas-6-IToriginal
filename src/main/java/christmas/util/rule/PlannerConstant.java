package christmas.util.rule;

public enum PlannerConstant {

    FREEBIES_TARGET(120000);

    private final Integer value;

    PlannerConstant(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
