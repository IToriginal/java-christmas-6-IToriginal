package christmas.service;

import static christmas.util.Parser.parseOrderInfo;
import static christmas.util.Parser.parseVisitDate;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Planner;
import christmas.repository.MemoryPlannerRepository;
import christmas.repository.PlannerRepository;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlannerServiceTest {

    private PlannerRepository plannerRepository;
    private PlannerService plannerService;

    @BeforeEach
    void setUp() {
        plannerRepository = new MemoryPlannerRepository();
        plannerService = new PlannerService(plannerRepository);
    }

    @AfterEach
    void afterTest() {
        plannerRepository.clearMemory();
    }

    @DisplayName("예약날짜가 정상적으로 메모리에 저장되는지 확인한다.")
    @Test
    void createPlannerMethodTest() {
        String input = "26";
        String orderMenu = "타파스-1,제로콜라-1";
        int day = parseVisitDate(input).getDayOfMonth();

        plannerService.createPlanner(input, orderMenu);

        assertThat(plannerRepository.findByReservationDate(parseVisitDate(input)))
                .hasValueSatisfying(planner -> assertThat(
                        planner.getReservationDate().getDayOfMonth()).isEqualTo(day));
    }

    @DisplayName("주문 메뉴가 정상적으로 메모리에 저장되는지 확인한다.")
    @Test
    void createPlannerWithValidOrderMenuTest() {
        String visitDateInput = "26";
        String orderMenuInput = "타파스-1,제로콜라-1";

        plannerService.createPlanner(visitDateInput, orderMenuInput);

        Planner planner = plannerRepository.findByReservationDate(parseVisitDate(visitDateInput)).orElseThrow();
        HashMap<String, Integer> expectedMenu = parseOrderInfo(orderMenuInput);
        assertThat(planner.getMenu()).isEqualTo(expectedMenu);
    }
}