package christmas.controller;

import christmas.service.PlannerCreator;
import christmas.service.PlannerQuery;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {

    private PlannerCreator plannerCreator;
    private PlannerQuery plannerQuery;

    public PlannerController(PlannerCreator plannerCreator, PlannerQuery plannerQuery) {
        this.plannerCreator = plannerCreator;
        this.plannerQuery = plannerQuery;
    }

    public void run() {
        OutputView.displayGreeting();
        reservation();
        reservationStatus();
    }

    private void reservation() {
        String visitDate = InputView.requestVisitDate();
        String order = InputView.requestOrder();
        plannerCreator.creatPlanner(visitDate , order);
    }

    private void reservationStatus() {
        Integer totalOrderAmount = plannerQuery.findTotalOrderAmount();
        Integer benefitsAmount = plannerQuery.findBenefitsAmount();

        OutputView.displayPreview(plannerQuery.findReservationDate());
        OutputView.displayOrderMenu(plannerQuery.findMenu());
        OutputView.displayTotalOrderAmount(totalOrderAmount);
        OutputView.displayFreebies(plannerQuery.findTotalOrderAmount());
        OutputView.displayBenefits(plannerQuery.findBenefits());
        OutputView.displayBenefitsAmount(benefitsAmount);
        OutputView.displayFinalPayment(totalOrderAmount, benefitsAmount);
        OutputView.displayEventBadge(plannerQuery.findEventBadge());
    }

}
