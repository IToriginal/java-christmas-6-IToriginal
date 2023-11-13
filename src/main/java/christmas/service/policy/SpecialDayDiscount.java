package christmas.service.policy;

import static christmas.util.content.InformationMessage.SPECIAL_DISCOUNT;

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
        int target = reservationDate.getDayOfWeek().getValue();
        // 이벤트 기간: 2023.12.01(금) ~ 2023.12.31(일)
        if (reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate)) {
            return 0;
        }

        if (target == 7 || reservationDate.getDayOfMonth() == 25) {
            return 1000;
        }

        return 0;
    }

}
