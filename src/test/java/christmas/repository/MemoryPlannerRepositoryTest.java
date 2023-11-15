package christmas.repository;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Planner;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("메모리 저장소 테스트: 값이 저장되어 있는지 Null인지를 확인한다.")
class MemoryPlannerRepositoryTest {

    private PlannerRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryPlannerRepository();
    }

    @AfterEach
    void cleanUp() {
        repository.clearMemory();
    }

    @DisplayName("예약 날짜 조회 테스트- 메모리가 비어있을 때")
    @Test
    void findReservationDateWhenMemoryIsEmpty() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findReservationDate().orElseThrow();
        });

        assertEquals("[ERROR] 예약된 날짜가 없습니다.", exception.getMessage());
    }

    @DisplayName("예약 날짜 조회 테스트 - 메모리에 데이터가 있을 때")
    @Test
    void findReservationDateWhenMemoryHasData() {
        Planner planner = new Planner();
        planner.setReservationDate(LocalDate.of(2023, 12, 25));

        repository.save(planner);

        assertEquals(
                LocalDate.of(2023, 12, 25),
                repository.findReservationDate().orElseThrow()
        );
    }

    @DisplayName("메뉴 조회 테스트 - 메모리가 비어있을 때")
    @Test
    void findMenuWhenMemoryIsEmpty() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findMenu().orElseThrow();
        });

        assertEquals("[ERROR] 예약된 메뉴가 없습니다.", exception.getMessage());
    }

    @DisplayName("메뉴 조회 테스트 - 메모리에 데이터가 있을 때")
    @Test
    void findMenuWhenMemoryHasData() {
        Planner planner = new Planner();
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("티본스테이크", 2);
        planner.setMenu(menu);

        repository.save(planner);

        assertEquals(menu, repository.findMenu().orElseThrow());
    }

    @DisplayName("총 주문 금액 조회 테스트 - 메모리가 비어있을 때")
    @Test
    void findTotalOrderAmountWhenMemoryIsEmpty() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findTotalOrderAmount().orElseThrow();
        });

        assertEquals("[ERROR] 예약된 메뉴가 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("총 주문 금액 조회 테스트 - 메모리에 데이터가 있을 때")
    void findTotalOrderAmountWhenMemoryHasData() {
        Planner planner = new Planner();
        planner.setTotalOrderAmount(10000);

        repository.save(planner);

        assertEquals(10000, repository.findTotalOrderAmount().orElseThrow());
    }

    @DisplayName("혜택 조회 테스트 - 메모리가 비어있을 때")
    @Test
    void findBenefitsWhenMemoryIsEmpty() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findBenefits().orElseThrow();
        });

        assertEquals("[ERROR] 예약된 메뉴가 없습니다.", exception.getMessage());
    }

    @DisplayName("혜택 조회 테스트 - 메모리에 데이터가 있을 때")
    @Test
    void findBenefitsWhenMemoryHasData() {
        Planner planner = new Planner();
        HashMap<String, Integer> benefits = new HashMap<>();
        benefits.put("할인 혜택", 2000);
        planner.setBenefits(benefits);

        repository.save(planner);

        assertEquals(benefits, repository.findBenefits().orElseThrow());
    }

    @DisplayName("혜택 금액 조회 테스트 - 메모리가 비어있을 때")
    @Test
    void findBenefitsAmountWhenMemoryIsEmpty() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findBenefitsAmount().orElseThrow();
        });

        assertEquals("[ERROR] 예약된 메뉴가 없습니다.", exception.getMessage());
    }

    @DisplayName("혜택 금액 조회 테스트 - 메모리에 데이터가 있을 때")
    @Test
    void findBenefitsAmountWhenMemoryHasData() {
        Planner planner = new Planner();
        planner.setBenefitsAmount(2000);

        repository.save(planner);

        assertEquals(2000, repository.findBenefitsAmount().orElseThrow());
    }

    @DisplayName("이벤트 배지 조회 테스트 - 메모리가 비어있을 때")
    @Test
    void findEventBadgeWhenMemoryIsEmpty() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findEventBadge().orElseThrow();
        });

        assertEquals("[ERROR] 예약된 메뉴가 없습니다.", exception.getMessage());
    }

    @DisplayName("이벤트 배지 조회 테스트 - 메모리에 데이터가 있을 때")
    @Test
    void findEventBadgeWhenMemoryHasData() {
        Planner planner = new Planner();
        planner.setEventBadge("산타");

        repository.save(planner);

        assertEquals("산타", repository.findEventBadge().orElseThrow());
    }

}