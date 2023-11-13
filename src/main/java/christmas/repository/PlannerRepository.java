package christmas.repository;

import christmas.domain.Planner;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public interface PlannerRepository {

    void save(Planner planner);

    Optional<LocalDate> findReservationDate();

    Optional<HashMap<String, Integer>> findMenu();

    Optional<Integer> findTotalOrderAmount();

    Optional<HashMap<String, Integer>> findBenefits();

    Optional<Integer> findBenefitsAmount();

    Optional<String> findEventBadge();

    void clearMemory();

}
