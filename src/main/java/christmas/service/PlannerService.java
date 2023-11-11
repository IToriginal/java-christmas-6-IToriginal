package christmas.service;

import static christmas.util.Parser.parseOrderInfo;
import static christmas.util.Parser.parseVisitDate;

import christmas.domain.Planner;
import christmas.repository.PlannerRepository;
import java.time.LocalDate;
import java.util.HashMap;

public class PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public void createPlanner(String visitDate, String orderInfo) {
        LocalDate reservationDate = parseVisitDate(visitDate);
        HashMap<String, Integer> menu = parseOrderInfo(orderInfo);

        Planner planner = new Planner();
        planner.setReservationDate(reservationDate);
        planner.setMenu(menu);

        plannerRepository.save(planner);
    }

}
