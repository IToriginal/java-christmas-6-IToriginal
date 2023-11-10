package christmas.util;

import java.time.LocalDate;

public class Parser {

    public static LocalDate parseVisitDate(String visitDate) {
        int day = Integer.parseInt(visitDate);
        LocalDate now = LocalDate.now();
        return LocalDate.of(now.getYear(), 12, day);
    }

}
