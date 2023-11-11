package christmas.repository;

import christmas.domain.Planner;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public interface PlannerRepository {

    Planner save(Planner planner);

    Optional<Planner> findByReservationDate(LocalDate reservationDate);

    Optional<LocalDate> findReservationDate();

    Optional<HashMap<String, Integer>> findMenu();

    Optional<Integer> findTotalOrderAmount();

    void clearMemory();

}
