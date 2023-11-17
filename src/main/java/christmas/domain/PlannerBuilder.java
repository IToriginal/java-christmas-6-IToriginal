package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;

public class PlannerBuilder {

    private LocalDate reservationDate;
    private HashMap<String, Integer> menu;
    private Integer totalOrderAmount;

    public PlannerBuilder withReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public PlannerBuilder withMenu(HashMap<String, Integer> menu) {
        this.menu = menu;
        return this;
    }

    public PlannerBuilder withTotalOrderAmount(Integer totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
        return this;
    }

    public Planner build() {
        Planner planner = new Planner();
        planner.setReservationDate(reservationDate);
        planner.setMenu(menu);
        planner.setTotalOrderAmount(totalOrderAmount);
        return planner;
    }

}
