package christmas.repository;

import christmas.domain.Planner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MemoryPlannerRepository implements PlannerRepository {

    private static final List<Planner> memory = new ArrayList<>();

    @Override
    public void save(Planner planner) {
        memory.add(planner);
    }

    @Override
    public Optional<LocalDate> findReservationDate() {
        return memory.stream()
                .findFirst()
                .map(Planner::getReservationDate);
    }

    @Override
    public Optional<HashMap<String, Integer>> findMenu() {
        return memory.stream()
                .findFirst()
                .map(Planner::getMenu);
    }

    @Override
    public Optional<Integer> findTotalOrderAmount() {
        return memory.stream()
                .findFirst()
                .map(Planner::getTotalOrderAmount);
    }

    @Override
    public Optional<HashMap<String, Integer>> findBenefits() {
        return memory.stream()
                .findFirst()
                .map(Planner::getBenefits);
    }

    @Override
    public Optional<Integer> findBenefitsAmount() {
        return memory.stream()
                .findFirst()
                .map(Planner::getBenefitsAmount);
    }

    @Override
    public Optional<String> findEventBadge() {
        return memory.stream()
                .findFirst()
                .map(Planner::getEventBadge);
    }

    @Override
    public void clearMemory() {
        memory.clear();
    }

}
