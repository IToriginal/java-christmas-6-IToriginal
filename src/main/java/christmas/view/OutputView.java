package christmas.view;

import static christmas.util.Parser.formatCurrency;
import static christmas.util.content.ErrorMessage.ERROR_WORD;
import static christmas.util.content.InformationMessage.BENEFITS_LIST;
import static christmas.util.content.InformationMessage.BENEFIT_AMOUNT;
import static christmas.util.content.InformationMessage.CHECK_LIST;
import static christmas.util.content.InformationMessage.CHECK_MENU;
import static christmas.util.content.InformationMessage.EVENT_BADGE;
import static christmas.util.content.InformationMessage.FINAL_PAYMENT;
import static christmas.util.content.InformationMessage.FREEBIES_MENU;
import static christmas.util.content.InformationMessage.FURE_MINUS;
import static christmas.util.content.InformationMessage.GREETING;
import static christmas.util.content.InformationMessage.NOTHING;
import static christmas.util.content.InformationMessage.PREVIEW_BENEFITS;
import static christmas.util.content.InformationMessage.SEPARATION_MINUS;
import static christmas.util.content.InformationMessage.TOTAL_ORDER_AMOUNT;
import static christmas.util.rule.PlannerConstant.FREEBIES_TARGET;
import static christmas.util.rule.RestaurantMenu.DRINK_CHAMPAGNE;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OutputView {

    public static void displayGreeting() {
        System.out.println(GREETING.getContent());
    }

    public static void displayPreview(LocalDate date) {
        int day = date.getDayOfMonth();
        String message = String.format(PREVIEW_BENEFITS.getContent(), day);
        System.out.println(message);
    }

    public static void displayOrderMenu(HashMap<String, Integer> menu) {
        System.out.println(CHECK_MENU.getContent());
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            System.out.printf(CHECK_LIST.getContent(), entry.getKey(), entry.getValue());
        }
    }

    public static void displayTotalOrderAmount(Integer totalOrderAmount) {
        System.out.println(TOTAL_ORDER_AMOUNT.getContent());
        System.out.println(formatCurrency(totalOrderAmount));
    }

    public static void displayFreebies(Integer totalOrderAmount) {
        System.out.println(FREEBIES_MENU.getContent());
        if (totalOrderAmount >= FREEBIES_TARGET.getValue()) {
            displayFreebie();
            return;
        }
        System.out.println(NOTHING.getContent());
    }

    private static void displayFreebie() {
        System.out.printf(CHECK_LIST.getContent(), DRINK_CHAMPAGNE.getName(), 1);
    }

    public static void displayBenefits(Map<String, Integer> benefits) {
        System.out.println(BENEFITS_LIST.getContent());
        int count = 0;
        for (Map.Entry<String, Integer> entry : benefits.entrySet()) {
            String content = entry.getKey();
            Integer discountPrice = entry.getValue();
            if (discountPrice > 0) {
                System.out.println(
                        content + SEPARATION_MINUS.getContent() + formatCurrency(discountPrice));
            }
            if (discountPrice == 0) {
                count++;
            }
        }
        if (count == 4) {
            System.out.println(NOTHING.getContent());
        }

    }

    public static void displayBenefitsAmount(Integer benefitsAmount) {
        System.out.println(BENEFIT_AMOUNT.getContent());
        if (benefitsAmount != 0) {
            System.out.println(FURE_MINUS.getContent() + formatCurrency(benefitsAmount));
        }
        if (benefitsAmount == 0) {
            System.out.println("0원");
        }
    }

    public static void displayFinalPayment(Integer totalOrderAmount, Integer benefitsAmount) {
        System.out.println(FINAL_PAYMENT.getContent());
        if (totalOrderAmount >= FREEBIES_TARGET.getValue()) {
            int payment = totalOrderAmount - benefitsAmount + DRINK_CHAMPAGNE.getPrice();
            System.out.println(formatCurrency(payment));
        }
        if (totalOrderAmount < FREEBIES_TARGET.getValue()) {
            int payment = totalOrderAmount - benefitsAmount;
            System.out.println(formatCurrency(payment));
        }
    }

    public static void displayEventBadge(String eventBadge) {
        System.out.println(EVENT_BADGE.getContent());
        System.out.println(eventBadge);
    }

    public static void displayErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_WORD.getContent() + e.getMessage());
    }
}
