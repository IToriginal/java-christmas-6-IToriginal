package christmas.view;

import static christmas.util.content.InformationMessage.GREETING;

public class OutputView {

    public static void displayGreeting() {
        System.out.println(GREETING.getContent());
    }

}
