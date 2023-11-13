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
        reservationStatus();
    }

    private void reservation() {
        String visitDate = InputView.requestVisitDate();
        String orderInfo = InputView.requestOrder();
        plannerService.createPlanner(visitDate , orderInfo);
    }

    private void reservationStatus() {
        Integer totalOrderAmount = plannerService.findTotalOrderAmount();
        Integer benefitsAmount = plannerService.findBenefitsAmount();

        OutputView.displayPreview(plannerService.findReservationDate());
        OutputView.displayOrderMenu(plannerService.findMenu());
        OutputView.displayTotalOrderAmount(totalOrderAmount);
        OutputView.displayFreebies(plannerService.findTotalOrderAmount());
        OutputView.displayBenefits(plannerService.findBenefits());
        OutputView.displayBenefitsAmount(benefitsAmount);
        OutputView.displayFinalPayment(totalOrderAmount, benefitsAmount);
    }

}
