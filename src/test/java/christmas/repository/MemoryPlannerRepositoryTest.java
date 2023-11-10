package christmas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Planner;
import christmas.util.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemoryPlannerRepositoryTest {

    private MemoryPlannerRepository repository;
    private Planner planner;

    @BeforeEach
    void setUp() {
        repository = new MemoryPlannerRepository();
        planner = new Planner();
    }

    @AfterEach
    void afterTest() {
        repository.clearMemory();
    }

    @DisplayName("입력한 날짜는 메모리에 저장된다.")
    @Test
    void save() {
        String input = "26";
        planner.setReservationDate(Parser.parseVisitDate(input));

        repository.save(planner);
        Planner date = repository.findByReservationDate(planner.getReservationDate()).get();

        assertThat(date).isEqualTo(planner);
    }

}