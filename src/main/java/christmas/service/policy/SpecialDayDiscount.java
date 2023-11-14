package christmas.service.policy;

import static christmas.util.content.InformationMessage.SPECIAL_DISCOUNT;
import static christmas.util.rule.PlannerConstant.CRISTMAS_DATE;
import static christmas.util.rule.PlannerConstant.SPECAIL_DISCOUNT;
import static christmas.util.rule.PlannerConstant.ZERO_VALUE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

public class SpecialDayDiscount implements Discount {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public SpecialDayDiscount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String benefitsContents(LocalDate reservationDate) {
        return SPECIAL_DISCOUNT.getContent();
    }

    @Override
    public Integer calculateDiscount(Integer totalOrderAmount, LocalDate reservationDate,
            HashMap<String, Integer> menuCount) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if (isDiscountableDate(reservationDate)) {
            return ZERO_VALUE.getValue();
        }

        if (isSpecialDay(reservationDate, dayOfWeek)) {
            return SPECAIL_DISCOUNT.getValue();
        }

        return ZERO_VALUE.getValue();
    }

    private boolean isSpecialDay(LocalDate reservationDate, DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SUNDAY
                || reservationDate.getDayOfMonth() == CRISTMAS_DATE.getValue();
    }

    private boolean isDiscountableDate(LocalDate reservationDate) {
        return reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate);
    }

}
