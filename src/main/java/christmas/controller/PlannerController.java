package christmas.controller;

import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {

    private PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    public void run() {
        OutputView.displayGreeting();
        reservation();
    }

    public void reservation() {
        String visitDate = InputView.requestVisitDate();
        plannerService.createPlanner(visitDate);
    }

}
