package christmas.service;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("할인 계산기 테스트")
class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;

    @BeforeEach
    void setUp() {
        discountCalculator = new DiscountCalculator();
    }

    @DisplayName("모든 할인 정책 계산이 올바르게 동작하는지 테스트")
    @Test
    void calculateDiscountTest() {
        LocalDate reservationDate = LocalDate.of(2023, 12, 25);
        HashMap<String, Integer> menuCount = new HashMap<>();
        menuCount.put("MAIN", 3); // 티본스테이크-3
        menuCount.put("DESSERT", 5); // 아이스크림-5
        menuCount.put("DRINK", 4); // 레드와인-4
        int totalOrderAmount = 430000;

        HashMap<String, Integer> result = discountCalculator.calculateDiscount(
                menuCount,
                totalOrderAmount,
                reservationDate);

        // 예상된 할인 결과
        int dateDiscount = 3400;
        int dayOfWeekDiscount = 10115; // 평일 할인: 디저트 메뉴 할인 (5 * 2,023원)
        int specialDayDiscount = 1000; // 특별 할인: 25일과 매주 일요일은 특별할인 대상 날짜 (1,000원)
        int freebiesEvent = 25000; // 증정 이벤트: 일정금액 (120,000원) 이상이므로 적용 대상

        assertThat(result)
                .containsExactly(
                        entry("크리스마스 디데이 할인", dateDiscount),
                        entry("평일 할인", dayOfWeekDiscount),
                        entry("특별 할인", specialDayDiscount),
                        entry("증정 이벤트", freebiesEvent)
                );
    }

}