package christmas.service.policy;

import static christmas.util.content.InformationMessage.WEEKDAY_DISCOUNT;
import static christmas.util.content.InformationMessage.WEEKEND_DISCOUNT;
import static christmas.util.rule.MenuCategory.MENU_CATEGORY_DESSERT;
import static christmas.util.rule.MenuCategory.MENU_CATEGORY_MAIN;
import static christmas.util.rule.PlannerConstant.WEEK_DISCOUNT_AMOUNT;
import static christmas.util.rule.PlannerConstant.ZERO_VALUE;

import christmas.util.rule.MenuCategory;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

public class DayOfWeekDiscount implements Discount {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public DayOfWeekDiscount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String benefitsContents(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if (isWeekend(dayOfWeek)) {
            return WEEKEND_DISCOUNT.getContent();
        }
        return WEEKDAY_DISCOUNT.getContent();
    }

    @Override
    public Integer calculateDiscount(Integer totalOrderAmount, LocalDate reservationDate,
            HashMap<String, Integer> menuCount) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();

        if (isDiscountableDate(reservationDate)) {
            return ZERO_VALUE.getValue();
        }

        if (isWeekend(dayOfWeek)) {
            return calculateDiscountForCategory(menuCount, MENU_CATEGORY_MAIN);
        }

        if (isWeekday(dayOfWeek)) {
            return calculateDiscountForCategory(menuCount, MENU_CATEGORY_DESSERT);
        }

        return ZERO_VALUE.getValue();
    }

    private Integer calculateDiscountForCategory(HashMap<String, Integer> menuCount,
            MenuCategory category) {
        return menuCount.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(category.getContent()))
                .mapToInt(entry -> entry.getValue() * WEEK_DISCOUNT_AMOUNT.getValue())
                .sum();
    }

    private boolean isDiscountableDate(LocalDate reservationDate) {
        return reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate);
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SUNDAY ||
                dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY;
    }

}
