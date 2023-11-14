package christmas.service.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DiscountParameters;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("평일, 주말 할인 기능 테스트")
class DayOfWeekDiscountTest {

    private DayOfWeekDiscount dayOfWeekDiscount;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        dayOfWeekDiscount = new DayOfWeekDiscount(startDate, endDate);
    }

    @DisplayName("할인 기간 내, 평일에 예약된 경우, 디저트 메뉴 할인 혜택이 적용된다.")
    @Test
    void dayOfWeekDiscountAppliedWeekdayTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 3);
        HashMap<String, Integer> menuCount = new HashMap<>();
        menuCount.put("DESSERT", 2);
        menuCount.put("MAIN", 3);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                menuCount);

        Integer discountPayment = dayOfWeekDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(2 * 2023);
    }

    @DisplayName("할인 기간 내, 주말에 예약된 경우, 메인 메뉴 할인 혜택이 적용된다.")
    @Test
    void dayOfWeekDiscountAppliedWeekendTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 2);
        HashMap<String, Integer> menuCount = new HashMap<>();
        menuCount.put("DESSERT", 2);
        menuCount.put("MAIN", 3);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                menuCount);

        Integer discountPayment = dayOfWeekDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(3 * 2023);
    }

    @DisplayName("할인 기간이 아닌 날짜에 예약된 경우, 아무 혜택이 적용되지 않는다.")
    @Test
    void dayOfWeekDiscountNotAppliedTest() {
        LocalDate reservationDate = LocalDate.of(2023, 11, 14);
        HashMap<String, Integer> menuCount = new HashMap<>();
        menuCount.put("DESSERT", 2);
        menuCount.put("MAIN", 3);
        DiscountParameters parameters = new DiscountParameters(
                10000,
                reservationDate,
                menuCount);

        Integer discountPayment = dayOfWeekDiscount.calculateDiscount(parameters);

        assertThat(discountPayment).isEqualTo(0);
    }
}