package christmas.service.policy;

import christmas.domain.DiscountParameters;
import java.time.LocalDate;

public interface Discount {

    String benefitsContents(LocalDate reservationDate);

    Integer calculateDiscount(DiscountParameters parameters);

}
