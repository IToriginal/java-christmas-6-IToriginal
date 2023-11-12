package christmas.service.policy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface Discount {

    String benefitsContents(LocalDate reservationDate);

    Integer calculateDiscount(Integer totalOrderAmount, LocalDate reservationDate,
            HashMap<String, Integer> menuCount);

}
