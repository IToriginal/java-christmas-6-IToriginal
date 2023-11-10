package christmas.service;

import static christmas.util.Parser.parseVisitDate;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.repository.MemoryPlannerRepository;
import christmas.repository.PlannerRepository;
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

    @DisplayName("예약날짜를 입력 받으면 값을 메모리에 저장한다.")
    @Test
    void createPlannerMethodTest() {
        String input = "26";
        int day = parseVisitDate(input).getDayOfMonth();

        // Act
        plannerService.createPlanner(input);

        // Assert
        assertThat(plannerRepository.findByReservationDate(parseVisitDate(input)))
                .hasValueSatisfying(planner -> assertThat(
                        planner.getReservationDate().getDayOfMonth()).isEqualTo(day));
    }

}