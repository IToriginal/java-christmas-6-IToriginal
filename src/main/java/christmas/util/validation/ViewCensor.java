package christmas.util.validation;

import static christmas.util.content.ErrorMessage.FORMAT_ERROR;
import static christmas.util.content.ErrorMessage.RESERVATION_DATE_ERROR;

import christmas.util.rule.RestaurantMenu;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ViewCensor {

    public static void validateReservationDate(String input) {
        isNumeric(input);
        inValidRange(input);
        isSpace(input);
    }

    public static void validateOrderMenu(String input) {
        inputFormatCheck(input);
        String[] order = input.split(",");
        Set<String> uniqueMenus = new HashSet<>();
        for (String menu : order) {
            String[] menuDetail = menu.split("-");
            String menuName = menuDetail[0].trim();
            String quantity = menuDetail[1].trim();
            isFormat(menuDetail);
            isQuantity(quantity);
            isMenu(menuName);
            isUnique(uniqueMenus, menuName);
        }
    }

    private static void inputFormatCheck(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }
    }

    private static void isUnique(Set<String> uniqueMenu, String menuName) {
        if (!uniqueMenu.add(menuName)) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }
    }

    private static void isMenu(String menuName) {
        if (!Arrays.stream(RestaurantMenu.values())
                .anyMatch(menu -> menu.getName().equalsIgnoreCase(menuName))) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }
    }

    private static void isQuantity(String quantity) {
        if (!quantity.matches("-?\\d+")) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }

        if (quantity.equals("0")) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }
    }

    private static void isFormat(String[] menuDetail) {
        if (menuDetail.length != 2) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }
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
