package christmas.service.policy;

import static christmas.util.content.InformationMessage.DATE_DISCOUNT;
import static christmas.util.rule.PlannerConstant.DEFAULT_DISCOUNT_AMOUNT;
import static christmas.util.rule.PlannerConstant.ZERO_VALUE;

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
        if (isDiscountableDate(reservationDate)) {
            return ZERO_VALUE.getValue();
        }
        return calculateDiscountAmount(reservationDate);
    }

    private boolean isDiscountableDate(LocalDate reservationDate) {
        return reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate);
    }

    private int calculateDiscountAmount(LocalDate reservationDate) {
        int daysSinceStart = (int) startDate.until(reservationDate, ChronoUnit.DAYS);
        return DEFAULT_DISCOUNT_AMOUNT.getValue() + daysSinceStart * dailyIncrease;
    }

}
