// TODO: 불필요한 코드 삭제로 테스트 재구성 필요

//package christmas.repository;
//
//import static christmas.util.Parser.parseOrderInfo;
//import static christmas.util.Parser.parseVisitDate;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import christmas.domain.Planner;
//import java.time.LocalDate;
//import java.util.HashMap;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class MemoryPlannerRepositoryTest {
//
//    private MemoryPlannerRepository repository;
//    private Planner planner;
//
//    @BeforeEach
//    void setUp() {
//        repository = new MemoryPlannerRepository();
//        planner = new Planner();
//    }
//
//    @AfterEach
//    void afterTest() {
//        repository.clearMemory();
//    }
//
//    @DisplayName("입력한 날짜와 메뉴는 메모리에 저장된다.")
//    @Test
//    void saveReservationDate() {
//        String inputDate = "26";
//        LocalDate visitDate = parseVisitDate(inputDate);
//        planner.setReservationDate(visitDate);
//
//        String inputOrder = "타파스-1,제로콜라-1";
//        HashMap<String, Integer> orderMenu = parseOrderInfo(inputOrder);
//        planner.setMenu(orderMenu);
//
//        repository.save(planner);
//        Planner savedPlanner = repository.findByReservationDate(visitDate).orElseThrow();
//
//        assertThat(savedPlanner.getReservationDate()).isEqualTo(visitDate);
//        assertThat(savedPlanner.getMenu()).isEqualTo(orderMenu);
//    }
//
//}