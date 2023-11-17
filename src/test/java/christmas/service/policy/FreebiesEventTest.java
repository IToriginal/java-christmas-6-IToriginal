package christmas.service.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DiscountParameters;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("증정 할인 혜택 기능 테스트")
class FreebiesEventTest {

    private FreebiesEvent freebiesEvent;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        freebiesEvent = new FreebiesEvent(startDate, endDate);
    }

    @DisplayName("할인 기간 내, 일정 금액 이상을 예약한 경우, 증정 할인 혜택이 적용된다.")
    @Test
    void freebiesEventDiscountAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 3);
        int totalOrderAmount = 120000;

        DiscountParameters parameters = new DiscountParameters(
                totalOrderAmount,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = freebiesEvent.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(25000);
    }

    @DisplayName("할인 기간 내, 일정 금액 미만으로 예약한 경우, 증정 할인 혜택이 적용되지 않는다.")
    @Test
    void freebiesEventDiscountNotAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 15); // 할인 기간 내
        int totalOrderAmount = 30000;

        DiscountParameters parameters = new DiscountParameters(
                totalOrderAmount,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = freebiesEvent.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(0);
    }

    @DisplayName("할인 기간 외에 예약된 경우, 할인 혜택이 적용되지 않는다.")
    @Test
    void discountNotAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 11, 14);
        int totalOrderAmount = 120000;

        DiscountParameters parameters = new DiscountParameters(
                totalOrderAmount,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = freebiesEvent.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(0);
    }

}