package christmas.service.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DiscountParameters;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("크리스마스 디데이 할인 기능 테스트")
class DateDiscountTest {

    private DateDiscount dateDiscount;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        int dailyIncrease = 100;
        dateDiscount = new DateDiscount(startDate, endDate, dailyIncrease);
    }

    @DisplayName("할인 기간 내에 예약된 경우, 할인 혜택이 적용된다. (기본 1,000원 부터 100원 씩 증가하는 혜택)")
    @Test
    void dateDiscountAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 3);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = dateDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(1200);
    }

    @DisplayName("할인 기간 외에 예약된 경우, 할인 혜택이 적용되지 않는다.")
    @Test
    void dateDiscountNotAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 11, 14);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = dateDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(0);
    }

}