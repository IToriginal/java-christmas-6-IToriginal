package christmas.service;

import static christmas.util.Parser.parseOrderInfo;
import static christmas.util.Parser.parseVisitDate;

import christmas.domain.Planner;
import christmas.repository.PlannerRepository;
import christmas.util.rule.RestaurantMenu;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
        planner.setTotalOrderAmount(calculateTotalOrderAmount(menu));

        plannerRepository.save(planner);
    }

    public Integer findTotalOrderAmount() {
        return plannerRepository.findTotalOrderAmount().get();
    }

    public LocalDate findReservationDate() {
        return plannerRepository.findReservationDate().get();
    }

    public HashMap<String, Integer> findMenu() {
        return plannerRepository.findMenu().get();
    }

    private Integer calculateTotalOrderAmount(HashMap<String, Integer> menu) {
        int totalOrderAmount = 0;
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            String menuName = entry.getKey();
            Integer quantity = entry.getValue();
            Integer menuPrice = getMenuTable(menuName);
            totalOrderAmount += menuPrice * quantity;
        }
        return totalOrderAmount;
    }

    private Integer getMenuTable(String menuName) {
        for (RestaurantMenu menu : RestaurantMenu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getPrice();
            }
        }
        return 0;
    }

}
