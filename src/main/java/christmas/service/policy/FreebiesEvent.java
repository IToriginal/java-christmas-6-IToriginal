package christmas.service.policy;

import static christmas.util.content.InformationMessage.FREEBIES_EVENT;
import static christmas.util.rule.PlannerConstant.FREEBIES_TARGET;
import static christmas.util.rule.PlannerConstant.ZERO_VALUE;
import static christmas.util.rule.RestaurantMenu.DRINK_CHAMPAGNE;

import java.time.LocalDate;
import java.util.HashMap;

public class FreebiesEvent implements Discount {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public FreebiesEvent(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String benefitsContents(LocalDate reservationDate) {
        return FREEBIES_EVENT.getContent();
    }

    @Override
    public Integer calculateDiscount(Integer totalOrderAmount, LocalDate reservationDate,
            HashMap<String, Integer> menuCount) {
        if (isDiscountableDate(reservationDate)) {
            return ZERO_VALUE.getValue();
        }

        if (isFreebiesTarget(totalOrderAmount)) {
            return DRINK_CHAMPAGNE.getPrice();
        }
        return ZERO_VALUE.getValue();
    }

    private boolean isDiscountableDate(LocalDate reservationDate) {
        return reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate);
    }

    private boolean isFreebiesTarget(Integer totalOrderAmount) {
        return totalOrderAmount >= FREEBIES_TARGET.getValue();
    }

}
