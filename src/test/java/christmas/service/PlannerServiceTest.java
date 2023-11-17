package christmas.service;

import static christmas.util.Parser.parseOrderInfo;
import static christmas.util.Parser.parseVisitDate;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.repository.MemoryPlannerRepository;
import christmas.repository.PlannerRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Planner 생성 테스트")
class PlannerServiceTest {

    private PlannerRepository repository;
    private DiscountCalculator discountCalculator;
    private PlannerCreator plannerCreator;

    @BeforeEach
    void setUp() {
        repository = new MemoryPlannerRepository();
        discountCalculator = new DiscountCalculator();
        plannerCreator = new PlannerCreator(repository, discountCalculator);
    }

    @AfterEach
    void cleanUp() {
        repository.clearMemory();
    }

    @DisplayName("예약 날짜 테스트")
    @Test
    void createPlannerMethodReservationDateTest() {
        String visitDate = "3";
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        LocalDate expectedData= parseVisitDate(visitDate); // "2023-12-03"

        plannerCreator.creatPlanner(visitDate, order);
        LocalDate actualData = repository.findReservationDate().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("메뉴 (이름, 가격) 테스트")
    @Test
    void createPlannerMethodMenuTest() {
        String visitDate = "3";
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        HashMap<String, Integer> expectedData= parseOrderInfo(order);

        plannerCreator.creatPlanner(visitDate, order);
        HashMap<String, Integer> actualData = repository.findMenu().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("총 주문 금액 테스트")
    @Test
    void createPlannerMethodTotalOrderAmountTest() {
        String visitDate = "3";
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        Integer expectedData = 142000;

        plannerCreator.creatPlanner(visitDate, order);
        Integer actualData = repository.findTotalOrderAmount().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("할인 혜택이 있는 경우 테스트")
    @Test
    void createPlannerMethodHasBenefitsTest() {
        String visitDate = "3";
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        HashMap<String, Integer> expectedData = new LinkedHashMap<>();
        expectedData.put("크리스마스 디데이 할인", 1200);
        expectedData.put("평일 할인", 4046);
        expectedData.put("특별 할인", 1000);
        expectedData.put("증정 이벤트", 25000);

        plannerCreator.creatPlanner(visitDate, order);
        HashMap<String, Integer> actualData = repository.findBenefits().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("할인 혜택이 없는 경우 테스트")
    @Test
    void createPlannerMethodBenefitsTest() {
        String visitDate = "26";
        String order = "타파스-1,제로콜라-1";

        HashMap<String, Integer> expectedData = new LinkedHashMap<>();
        expectedData.put("크리스마스 디데이 할인", 0);
        expectedData.put("평일 할인", 0);
        expectedData.put("특별 할인", 0);
        expectedData.put("증정 이벤트", 0);

        plannerCreator.creatPlanner(visitDate, order);
        HashMap<String, Integer> actualData = repository.findBenefits().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("총 할인 금액 테스트")
    @Test
    void createPlannerMethodBenefitsAmountTest() {
        String visitDate = "3";
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        Integer expectedData = 31246;

        plannerCreator.creatPlanner(visitDate, order);
        Integer actualData = repository.findBenefitsAmount().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("이벤트 배지 테스트 - 산타")
    @Test
    void createPlannerMethodEventBadgeSantaTest() {
        String visitDate = "3";
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        String expectedData = "산타";

        plannerCreator.creatPlanner(visitDate, order);
        String actualData = repository.findEventBadge().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("이벤트 배지 테스트 - 트리")
    @Test
    void createPlannerMethodEventBadgeTreeTest() {
        String visitDate = "25";
        String order = "초코케이크-5"; // 크리스마스 디데이 할인 = 3,400, 평일 할인 = 10,115, 특별 할인 = 1,000

        String expectedData = "트리"; // 해택 금액: 14,515원

        plannerCreator.creatPlanner(visitDate, order);
        String actualData = repository.findEventBadge().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("이벤트 배지 테스트 - 별")
    @Test
    void createPlannerMethodEventBadgeStarTest() {
        String visitDate = "25";
        String order = "초코케이크-2"; // 크리스마스 디데이 할인 = 3,400, 평일 할인 = 4,046, 특별 할인 = 1,000

        String expectedData = "별"; // 해택 금액: 8,446원

        plannerCreator.creatPlanner(visitDate, order);
        String actualData = repository.findEventBadge().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

    @DisplayName("이벤트 배지 테스트 - 없음")
    @Test
    void createPlannerMethodEventBadgeNottingTest() {
        String visitDate = "2";
        String order = "아이스크림-2"; // 크리스마스 디데이 할인 = 1,100

        String expectedData = "없음"; // 해택 금액: 1,100원

        plannerCreator.creatPlanner(visitDate, order);
        String actualData = repository.findEventBadge().get();

        assertThat(actualData).isEqualTo(expectedData);
    }

}