package christmas.view;

import static christmas.util.content.ErrorMessage.ERROR_WORD;
import static christmas.util.content.InformationMessage.CHECK_MENU;
import static christmas.util.content.InformationMessage.CHECK_ORDER_LIST;
import static christmas.util.content.InformationMessage.GREETING;
import static christmas.util.content.InformationMessage.PREVIEW_BENEFITS;

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
            System.out.printf(CHECK_ORDER_LIST.getContent(), entry.getKey(), entry.getValue());
        }
    }

    public static void displayErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_WORD.getContent() + e.getMessage());
    }
}
