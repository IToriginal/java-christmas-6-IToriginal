package christmas.view;

import static christmas.util.content.InformationMessage.ORDER_MENU;
import static christmas.util.content.InformationMessage.VISIT_DATE;
import static christmas.util.validation.ViewCensor.validateOrderMenu;
import static christmas.util.validation.ViewCensor.validateReservationDate;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String requestVisitDate() {
        System.out.println(VISIT_DATE.getContent());
        String input = Console.readLine();
        try {
            validateReservationDate(input);
        } catch (IllegalArgumentException e) {
            OutputView.displayErrorMessage(e);
            return requestVisitDate();
        }
        return input;
    }

    public static String requestOrder() {
        System.out.println(ORDER_MENU.getContent());
        String input = Console.readLine();
        try {
            validateOrderMenu(input);
        } catch (IllegalArgumentException e) {
            OutputView.displayErrorMessage(e);
            return requestOrder();
        }
        return input;
    }

}
