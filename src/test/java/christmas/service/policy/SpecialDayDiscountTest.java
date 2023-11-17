package christmas.service.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DiscountParameters;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("특별 할인 기능 테스트")
class SpecialDayDiscountTest {

    private SpecialDayDiscount specialDayDiscount;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        specialDayDiscount = new SpecialDayDiscount(startDate, endDate);
    }

    @DisplayName("할인 기간 내, 특별한 날(크리스마스 당일)에 예약된 경우, 할인 혜택이 적용된다.")
    @Test
    void specialDayChristmasDiscountAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 25);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = specialDayDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(1000);
    }

    @DisplayName("할인 기간 내, 특별한 날(일요일)에 예약된 경우, 할인 혜택이 적용된다.")
    @Test
    void specialDaySundayDiscountAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 3);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = specialDayDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(1000);
    }

    @DisplayName("할인 기간이 아닌, 특별한 날(일요일)에 예약된 경우, 할인 혜택이 적용되지 않는다.")
    @Test
    void specialDaySundayDiscountNotAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 11, 19);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                new HashMap<>());

        Integer discountPayment = specialDayDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(0);
    }

}