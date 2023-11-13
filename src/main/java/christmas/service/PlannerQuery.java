package christmas.service;

import christmas.repository.PlannerRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PlannerQuery {

    private final PlannerRepository repository;

    public PlannerQuery(PlannerRepository repository) {
        this.repository = repository;
    }

    public Map<String, Integer> findBenefits() {
        return repository.findBenefits().get();
    }

    public Integer findTotalOrderAmount() {
        return repository.findTotalOrderAmount().get();
    }

    public LocalDate findReservationDate() {
        return repository.findReservationDate().get();
    }

    public HashMap<String, Integer> findMenu() {
        return repository.findMenu().get();
    }

    public Integer findBenefitsAmount() {
        return repository.findBenefitsAmount().get();
    }

    public String findEventBadge() {
        return repository.findEventBadge().get();
    }

}
