package christmas.repository;

import static christmas.util.content.ErrorMessage.ERROR_WORD;
import static christmas.util.content.ErrorMessage.REPO_MENU_ERROR;
import static christmas.util.content.ErrorMessage.REPO_RESERVATION_ERROR;

import christmas.domain.Planner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
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
                .map(Planner::getReservationDate)
                .map(Optional::ofNullable)
                .orElseThrow(() -> new NoSuchElementException(
                        ERROR_WORD.getContent() + REPO_RESERVATION_ERROR.getContent()));
    }

    @Override
    public Optional<HashMap<String, Integer>> findMenu() {
        return memory.stream()
                .findFirst()
                .map(Planner::getMenu)
                .map(Optional::ofNullable)
                .orElseThrow(() -> new NoSuchElementException(
                        ERROR_WORD.getContent() + REPO_MENU_ERROR.getContent()));
    }

    @Override
    public Optional<Integer> findTotalOrderAmount() {
        return memory.stream()
                .findFirst()
                .map(Planner::getTotalOrderAmount)
                .map(Optional::ofNullable)
                .orElseThrow(() -> new NoSuchElementException(
                        ERROR_WORD.getContent() + REPO_MENU_ERROR.getContent()));
    }

    @Override
    public Optional<HashMap<String, Integer>> findBenefits() {
        return memory.stream()
                .findFirst()
                .map(Planner::getBenefits)
                .map(Optional::ofNullable)
                .orElseThrow(() -> new NoSuchElementException(
                        ERROR_WORD.getContent() + REPO_MENU_ERROR.getContent()));
    }

    @Override
    public Optional<Integer> findBenefitsAmount() {
        return memory.stream()
                .findFirst()
                .map(Planner::getBenefitsAmount)
                .map(Optional::ofNullable)
                .orElseThrow(() -> new NoSuchElementException(
                        ERROR_WORD.getContent() + REPO_MENU_ERROR.getContent()));
    }

    @Override
    public Optional<String> findEventBadge() {
        return memory.stream()
                .findFirst()
                .map(Planner::getEventBadge)
                .map(Optional::ofNullable)
                .orElseThrow(() -> new NoSuchElementException(
                        ERROR_WORD.getContent() + REPO_MENU_ERROR.getContent()));
    }

    @Override
    public void clearMemory() {
        memory.clear();
    }

}
