package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;

public class DiscountParameters {

    private final int totalOrderAmount;
    private final LocalDate reservationDate;
    private final HashMap<String, Integer> menuCount;

    public DiscountParameters(int totalOrderAmount, LocalDate reservationDate, HashMap<String, Integer> menuCount) {
        this.totalOrderAmount = totalOrderAmount;
        this.reservationDate = reservationDate;
        this.menuCount = menuCount;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public HashMap<String, Integer> getMenuCount() {
        return menuCount;
    }

}
