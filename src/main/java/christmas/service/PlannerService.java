package christmas.service;

import static christmas.util.Parser.parseVisitDate;

import christmas.domain.Planner;
import christmas.repository.PlannerRepository;
import java.time.LocalDateTime;

public class PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public void createPlanner(String visitDate) {
        LocalDateTime reservationDate = parseVisitDate(visitDate);

        Planner planner = new Planner();
        planner.setReservationDate(reservationDate);

        plannerRepository.save(planner);
    }

}
