package christmas.view;

import static christmas.util.content.InformationMessage.VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String requestVisitDate() {
        System.out.println(VISIT_DATE.getContent());
        return Console.readLine();
    }

}
