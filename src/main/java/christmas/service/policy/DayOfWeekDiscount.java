package christmas.service.policy;

import static christmas.util.content.InformationMessage.MENU_CATEGORY_DESSERT;
import static christmas.util.content.InformationMessage.MENU_CATEGORY_MAIN;
import static christmas.util.content.InformationMessage.WEEKDAY_DISCOUNT;
import static christmas.util.content.InformationMessage.WEEKEND_DISCOUNT;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DayOfWeekDiscount implements Discount {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public DayOfWeekDiscount(LocalDate startDate,
            LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String benefitsContents(LocalDate reservationDate) {
        int target = reservationDate.getDayOfWeek().getValue();
        if (target !=  5 && target != 6) {
            return WEEKDAY_DISCOUNT.getContent();
        }
        return WEEKEND_DISCOUNT.getContent();
    }

    @Override
    public Integer calculateDiscount(Integer totalOrderAmount, LocalDate reservationDate,
            HashMap<String, Integer> menuCount) {
        // TODO: 컨벤션에 맞게 리팩토링 필요
        int target = reservationDate.getDayOfWeek().getValue();
        // 이벤트 기간: 2023.12.01(금) ~ 2023.12.31(일)
        if (reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate)) {
            return 0;
        }

        // 주말 할인 (금,토)
        if (target == 5 || target == 6) {
            for (Map.Entry<String, Integer> entry : menuCount.entrySet()) {
                if (entry.getKey().equals(MENU_CATEGORY_MAIN.getContent())) {
                    return entry.getValue() * 2023;
                }
            }
        }

        // 평일 할인 (일,월,화,수,목)
        if (target !=  5 && target != 6) {
            for (Map.Entry<String, Integer> entry : menuCount.entrySet()) {
                if (entry.getKey().equals(MENU_CATEGORY_DESSERT.getContent())) {
                    return entry.getValue() * 2023;
                }
            }
        }
        return 0;
    }

}
