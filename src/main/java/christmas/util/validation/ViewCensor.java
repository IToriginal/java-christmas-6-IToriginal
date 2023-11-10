package christmas.util.validation;

import static christmas.util.content.ErrorMessage.RESERVATION_DATE_ERROR;

public class ViewCensor {

    public static void validateReservationDate(String input) {
        isNumeric(input);
        inValidRange(input);
        isSpace(input);
    }

    private static void isNumeric(String input) {
        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException(RESERVATION_DATE_ERROR.getContent());
        }
    }

    private static void inValidRange(String input) {
        int day = Integer.parseInt(input);
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(RESERVATION_DATE_ERROR.getContent());
        }
    }

    private static void isSpace(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(RESERVATION_DATE_ERROR.getContent());
        }
    }

}
