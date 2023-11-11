package christmas.service.policy;

import java.time.LocalDate;

public interface Discount {

    String benefitsContents();
    Integer calculateDiscount(Integer totalOrderAmount, LocalDate reservationDate);

}
