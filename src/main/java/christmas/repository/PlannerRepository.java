package christmas.repository;

import christmas.domain.Planner;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PlannerRepository {

    Planner save(Planner planner);

    Optional<Planner> findByReservationDate(LocalDateTime reservationDate);

    void clearMemory();

}
