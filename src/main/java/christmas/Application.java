package christmas;

import christmas.controller.PlannerController;
import christmas.repository.MemoryPlannerRepository;
import christmas.repository.PlannerRepository;
import christmas.service.DiscountCalculator;
import christmas.service.PlannerCreator;
import christmas.service.PlannerQuery;

public class Application {

    public static void main(String[] args) {

        PlannerRepository plannerRepository = new MemoryPlannerRepository();
        PlannerQuery plannerQuery = new PlannerQuery(plannerRepository);
        DiscountCalculator discountCalculator = new DiscountCalculator();
        PlannerCreator plannerCreator = new PlannerCreator(plannerRepository, discountCalculator);
        PlannerController eventManager = new PlannerController(plannerCreator, plannerQuery);
        eventManager.run();

    }

}
