package christmas.view;

import static christmas.util.content.ErrorMessage.ERROR_WORD;
import static christmas.util.content.InformationMessage.GREETING;

public class OutputView {

    public static void displayGreeting() {
        System.out.println(GREETING.getContent());
    }

    public static void displayErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_WORD.getContent() + e.getMessage());
    }

}
