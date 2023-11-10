package christmas.repository;

import christmas.domain.Planner;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

public class MemoryPlannerRepository implements PlannerRepository {

    private static HashMap<Long, Planner> memory = new LinkedHashMap<>();
    private static long sequence = 0L;

    @Override
    public Planner save(Planner planner) {
        planner.setId(++sequence);
        memory.put(planner.getId(), planner);
        return planner;
    }

    @Override
    public Optional<Planner> findByReservationDate(LocalDateTime reservationDate) {
        return memory.values().stream()
                .filter(planner -> planner.getReservationDate().equals(reservationDate))
                .findAny();
    }

    @Override
    public void clearMemory() {
        memory.clear();
    }

}
