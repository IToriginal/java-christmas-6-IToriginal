package christmas.service;

import static christmas.util.rule.PlannerConstant.CRISTMAS_DATE;
import static christmas.util.rule.PlannerConstant.DAILY_INCREASE;
import static christmas.util.rule.PlannerConstant.DECEMBER;
import static christmas.util.rule.PlannerConstant.END_DATE;
import static christmas.util.rule.PlannerConstant.START_DATE;
import static christmas.util.rule.PlannerConstant.YEAR;

import christmas.service.policy.DateDiscount;
import christmas.service.policy.DayOfWeekDiscount;
import christmas.service.policy.Discount;
import christmas.service.policy.FreebiesEvent;
import christmas.service.policy.SpecialDayDiscount;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DiscountCalculator {

    private Discount dateDiscount;
    private Discount weekDiscount;
    private Discount specialDiscount;
    private Discount freebiesEvent;

    public DiscountCalculator() {
        dateDiscount = new DateDiscount(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), CRISTMAS_DATE.getValue()),
                DAILY_INCREASE.getValue()
        );
        weekDiscount = new DayOfWeekDiscount(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), END_DATE.getValue())
        );
        specialDiscount = new SpecialDayDiscount(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), END_DATE.getValue())
        );
        freebiesEvent = new FreebiesEvent(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), END_DATE.getValue())
        );
    }

    public HashMap<String, Integer> calculateDiscount(HashMap<String, Integer> menuCount,
            int totalOrderAmount, LocalDate reservationDate) {
        return calculateTotalDiscount(
                menuCount,
                totalOrderAmount,
                reservationDate,
                dateDiscount, weekDiscount, specialDiscount, freebiesEvent
        );
    }

    private HashMap<String, Integer> calculateTotalDiscount(HashMap<String, Integer> menuCount,
            int totalOrderAmount, LocalDate reservationDate, Discount... discountPolicies) {
        HashMap<String, Integer> benefits = new LinkedHashMap<>();
        for (Discount policy : discountPolicies) {
            String benefitContent = policy.benefitsContents(reservationDate);
            Integer benefitPrice = policy.calculateDiscount(
                    totalOrderAmount, reservationDate, menuCount);
            benefits.put(benefitContent, benefitPrice);
        }
        return benefits;
    }

}
