package christmas.controller;

import christmas.service.PlannerCreator;
import christmas.service.PlannerQuery;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {

    private final PlannerCreator plannerCreator;
    private final PlannerQuery plannerQuery;

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
        displayReservationInfo();
        displayTotalOrderInfo(totalOrderAmount);
        displayBenefitsInfo(benefitsAmount);
        displayFinalPaymentInfo(totalOrderAmount, benefitsAmount);
    }

    private void displayReservationInfo() {
        OutputView.displayPreview(plannerQuery.findReservationDate());
        OutputView.displayOrderMenu(plannerQuery.findMenu());
    }

    private void displayTotalOrderInfo(Integer totalOrderAmount) {
        OutputView.displayTotalOrderAmount(totalOrderAmount);
        OutputView.displayFreebies(totalOrderAmount);
    }

    private void displayBenefitsInfo(Integer benefitsAmount) {
        OutputView.displayBenefits(plannerQuery.findBenefits());
        OutputView.displayBenefitsAmount(benefitsAmount);
    }

    private void displayFinalPaymentInfo(Integer totalOrderAmount, Integer benefitsAmount) {
        OutputView.displayFinalPayment(totalOrderAmount, benefitsAmount);
        OutputView.displayEventBadge(plannerQuery.findEventBadge());
    }

}
