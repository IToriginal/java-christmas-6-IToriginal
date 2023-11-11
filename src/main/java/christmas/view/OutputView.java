package christmas.view;

import static christmas.util.Parser.formatCurrency;
import static christmas.util.content.ErrorMessage.ERROR_WORD;
import static christmas.util.content.InformationMessage.CHECK_MENU;
import static christmas.util.content.InformationMessage.CHECK_ORDER_LIST;
import static christmas.util.content.InformationMessage.GREETING;
import static christmas.util.content.InformationMessage.PREVIEW_BENEFITS;
import static christmas.util.content.InformationMessage.TOTAL_ORDER_AMOUNT;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
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
            System.out.printf(CHECK_ORDER_LIST.getContent(), entry.getKey(), entry.getValue());
        }
    }

    public static void displayTotalOrderAmount(Integer totalOrderAmount) {
        System.out.println(TOTAL_ORDER_AMOUNT.getContent());
        System.out.println(formatCurrency(totalOrderAmount));
    }

    public static void displayErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_WORD.getContent() + e.getMessage());
    }
}
