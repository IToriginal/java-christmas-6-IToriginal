package christmas.service;

import static christmas.util.Parser.parseCategoryCount;
import static christmas.util.Parser.parseOrderInfo;
import static christmas.util.Parser.parseVisitDate;
import static christmas.util.content.InformationMessage.BADGE_SANTA;
import static christmas.util.content.InformationMessage.BADGE_STAR;
import static christmas.util.content.InformationMessage.BADGE_TREE;
import static christmas.util.content.InformationMessage.NOTHING;

import christmas.domain.Planner;
import christmas.domain.PlannerBuilder;
import christmas.repository.PlannerRepository;
import christmas.util.rule.RestaurantMenu;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

public class PlannerCreator {

    private final PlannerRepository repository;
    private final DiscountCalculator discountCalculator;

    public PlannerCreator(PlannerRepository repository, DiscountCalculator discountCalculator) {
        this.repository = repository;
        this.discountCalculator = discountCalculator;
    }

    public void creatPlanner(String visitDate, String order) {
        LocalDate reservationDate = parseVisitDate(visitDate);
        HashMap<String, Integer> menu = parseOrderInfo(order);
        HashMap<String, Integer> foodType = parseCategoryCount(menu);

        Planner planner = new PlannerBuilder()
                .withReservationDate(reservationDate)
                .withMenu(menu)
                .withTotalOrderAmount(calculateTotalOrderAmount(menu))
                .build();

        setBenefitsAndAmount(planner, foodType);
        setEventBadge(planner);
        repository.save(planner);
    }

    private void setEventBadge(Planner planner) {
        planner.setEventBadge(calculateEventBadge(planner.getBenefitsAmount()));
    }

    private void setBenefitsAndAmount(Planner planner, HashMap<String, Integer> foodType) {
        HashMap<String, Integer> benefits = discountCalculator.calculateDiscount(
                foodType,
                planner.getTotalOrderAmount(),
                planner.getReservationDate()
        );
        planner.setBenefits(benefits);
        planner.setBenefitsAmount(calculateTotalDiscountAmount(benefits));
    }

    private String calculateEventBadge(Integer totalDiscountAmount) {
        if (totalDiscountAmount < 5000) {
            return NOTHING.getContent();
        }
        if (totalDiscountAmount < 10000) {
            return BADGE_STAR.getContent();
        }
        if (totalDiscountAmount < 20000) {
            return BADGE_TREE.getContent();
        }
        return BADGE_SANTA.getContent();
    }

    private Integer calculateTotalDiscountAmount(HashMap<String, Integer> discountBenefits) {
        return discountBenefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer calculateTotalOrderAmount(HashMap<String, Integer> menu) {
        return menu.entrySet().stream()
                .mapToInt(entry -> getMenuTable(entry.getKey()) * entry.getValue())
                .sum();
    }

    private Integer getMenuTable(String menuName) {
        return Arrays.stream(RestaurantMenu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .map(RestaurantMenu::getPrice)
                .orElse(0);
    }

}