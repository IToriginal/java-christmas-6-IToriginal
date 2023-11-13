package christmas.service;

import static christmas.util.Parser.parseCategoryCount;
import static christmas.util.Parser.parseOrderInfo;
import static christmas.util.Parser.parseVisitDate;
import static christmas.util.rule.PlannerConstant.CRISTMAS_DATE;
import static christmas.util.rule.PlannerConstant.DAILY_INCREASE;
import static christmas.util.rule.PlannerConstant.DECEMBER;
import static christmas.util.rule.PlannerConstant.END_DATE;
import static christmas.util.rule.PlannerConstant.START_DATE;
import static christmas.util.rule.PlannerConstant.YEAR;

import christmas.domain.Planner;
import christmas.repository.PlannerRepository;
import christmas.service.policy.DateDiscount;
import christmas.service.policy.DayOfWeekDiscount;
import christmas.service.policy.Discount;
import christmas.service.policy.FreebiesEvent;
import christmas.service.policy.SpecialDayDiscount;
import christmas.util.rule.RestaurantMenu;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlannerService {

    private final PlannerRepository plannerRepository;
    private Discount dateRangeDiscount;
    private Discount weekDayDiscount;
    private Discount specialDiscount;
    private Discount freebiesEvent;

    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
        dateRangeDiscount = new DateDiscount(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), CRISTMAS_DATE.getValue()),
                DAILY_INCREASE.getValue()
        );
        weekDayDiscount = new DayOfWeekDiscount(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), END_DATE.getValue())
        );
        specialDiscount = new SpecialDayDiscount(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), END_DATE.getValue())
        );
        freebiesEvent = new FreebiesEvent(
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), START_DATE.getValue()),
                LocalDate.of(YEAR.getValue(), DECEMBER.getValue(), END_DATE.getValue())
        );
    }

    public void createPlanner(String visitDate, String orderInfo) {
        LocalDate reservationDate = parseVisitDate(visitDate);
        HashMap<String, Integer> menu = parseOrderInfo(orderInfo);
        HashMap<String, Integer> menuCount = parseCategoryCount(menu);

        Planner planner = new Planner();
        planner.setReservationDate(reservationDate);
        planner.setMenu(menu);
        planner.setTotalOrderAmount(calculateTotalOrderAmount(menu));

        HashMap<String, Integer> discountBenefits = discountPrice(menu, reservationDate, menuCount);
        planner.setBenefits(discountBenefits);

        // 총 할인 금액 계산 및 설정
        Integer totalDiscountAmount = calculateTotalDiscountAmount(discountBenefits);
        planner.setBenefitsAmount(totalDiscountAmount);

        plannerRepository.save(planner);
    }

    public Map<String, Integer> findBenefits() {
        return plannerRepository.findBenefits().get();
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

    public Integer findBenefitsAmount() {
        return plannerRepository.findBenefitsAmount().get();
    }

    private Integer calculateTotalDiscountAmount(HashMap<String, Integer> discountBenefits) {
        int totalDiscountAmount = 0;
        for (int discountAmount : discountBenefits.values()) {
            totalDiscountAmount += discountAmount;
        }
        return totalDiscountAmount;
    }

    private HashMap<String, Integer> discountPrice(HashMap<String, Integer> menu,
            LocalDate reservationDate, HashMap<String, Integer> menuCount) {
        int totalOrderAmount = calculateTotalOrderAmount(menu);
        return calculateTotalDiscount(menuCount, totalOrderAmount, reservationDate,
                dateRangeDiscount, weekDayDiscount, specialDiscount, freebiesEvent);
    }

    private HashMap<String, Integer> calculateTotalDiscount(HashMap<String, Integer> menuCount,
            int totalOrderAmount, LocalDate reservationDate,
            Discount... discountPolicies) {
        HashMap<String, Integer> benefits = new LinkedHashMap<>();

        for (Discount discountPolicy : discountPolicies) {
            String benefitsContents = discountPolicy.benefitsContents(reservationDate);
            Integer discountPrice = discountPolicy.calculateDiscount(totalOrderAmount,
                    reservationDate, menuCount);
            benefits.put(benefitsContents, discountPrice);
        }
        return benefits;
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
