package christmas.service.policy;

import static christmas.util.content.InformationMessage.DATE_DISCOUNT;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class DateDiscount implements Discount {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int dailyIncrease;

    public DateDiscount(LocalDate startDate, LocalDate endDate, int dailyIncrease) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyIncrease = dailyIncrease;
    }

    @Override
    public String benefitsContents(LocalDate reservationDate) {
        return DATE_DISCOUNT.getContent();
    }

    @Override
    public Integer calculateDiscount(Integer totalOrderAmount, LocalDate reservationDate,
            HashMap<String, Integer> menuCount) {
        if (reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate)) {
            return 0;
        }

        int daysSinceStart = (int) startDate.until(reservationDate, ChronoUnit.DAYS);
        return Math.max(0, 1000 + daysSinceStart * dailyIncrease);
    }

}
