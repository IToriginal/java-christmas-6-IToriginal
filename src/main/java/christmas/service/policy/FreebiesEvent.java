package christmas.service.policy;

import static christmas.util.content.InformationMessage.FREEBIES_EVENT;
import static christmas.util.rule.PlannerConstant.FREEBIES_TARGET;

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
        // 이벤트 기간: 2023.12.01(금) ~ 2023.12.31(일)
        if (reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate)) {
            return 0;
        }

        if (totalOrderAmount >= FREEBIES_TARGET.getValue()) {
            return 25000;
        }
        return 0;
    }

}
