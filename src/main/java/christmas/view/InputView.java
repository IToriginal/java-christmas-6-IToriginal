package christmas.view;

import static christmas.util.content.InformationMessage.VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.validation.ViewCensor;

public class InputView {

    public static String requestVisitDate() {
        System.out.println(VISIT_DATE.getContent());
        String input = Console.readLine();
        try {
            ViewCensor.validateReservationDate(input);
        } catch (IllegalArgumentException e) {
            OutputView.displayErrorMessage(e);
            return requestVisitDate();
        }
        return input;
    }

}
