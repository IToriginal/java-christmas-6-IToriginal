package christmas;

import christmas.controller.PlannerController;
import christmas.repository.MemoryPlannerRepository;
import christmas.repository.PlannerRepository;
import christmas.service.DiscountCalculator;
import christmas.service.PlannerCreator;
import christmas.service.PlannerQuery;

public class Config {

    private final PlannerController plannerController;

    public Config() {
        PlannerRepository plannerRepository = new MemoryPlannerRepository();
        PlannerQuery plannerQuery = new PlannerQuery(plannerRepository);
        DiscountCalculator discountCalculator = new DiscountCalculator();
        PlannerCreator plannerCreator = new PlannerCreator(plannerRepository, discountCalculator);
        this.plannerController = new PlannerController(plannerCreator, plannerQuery);
    }

    public PlannerController getPlannerController() {
        return plannerController;
    }

}
