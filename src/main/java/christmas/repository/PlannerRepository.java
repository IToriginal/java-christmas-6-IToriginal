package christmas.repository;

import christmas.domain.Planner;
import java.time.LocalDate;
import java.util.Optional;

public interface PlannerRepository {

    Planner save(Planner planner);

    Optional<Planner> findByReservationDate(LocalDate reservationDate);

    void clearMemory();

}
