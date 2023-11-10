package christmas;

import christmas.controller.PlannerController;
import christmas.repository.MemoryPlannerRepository;
import christmas.repository.PlannerRepository;
import christmas.service.PlannerService;

public class Application {

    public static void main(String[] args) {

        PlannerRepository plannerRepository = new MemoryPlannerRepository();
        PlannerService plannerService = new PlannerService(plannerRepository);
        PlannerController eventManager = new PlannerController(plannerService);
        eventManager.run();

    }

}
