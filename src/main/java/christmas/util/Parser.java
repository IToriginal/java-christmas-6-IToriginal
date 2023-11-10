package christmas.util;

import java.time.LocalDateTime;

public class Parser {

    public static LocalDateTime parseVisitDate(String visitDate) {
        int day = Integer.parseInt(visitDate);
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(now.getYear(), 12, day, 0, 0);
    }

}
