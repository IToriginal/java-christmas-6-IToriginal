package christmas.repository;

import christmas.domain.Planner;
import java.time.LocalDate;
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
    public Optional<Planner> findByReservationDate(LocalDate reservationDate) {
        return memory.values().stream()
                .filter(planner -> planner.getReservationDate().equals(reservationDate))
                .findAny();
    }

    @Override
    public Optional<LocalDate> findReservationDate() {
        return memory.values().stream()
                .findAny()
                .map(Planner::getReservationDate);
    }

    @Override
    public Optional<HashMap<String, Integer>> findMenu() {
        return memory.values().stream()
                .findAny()
                .map(Planner::getMenu);
    }

    @Override
    public Optional<Integer> findTotalOrderAmount() {
        return memory.values().stream()
                .findAny()
                .map(Planner::getTotalOrderAmount);
    }

    @Override
    public void clearMemory() {
        memory.clear();
    }

}
