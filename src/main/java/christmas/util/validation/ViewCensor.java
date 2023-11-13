package christmas.util.validation;

import static christmas.util.content.ErrorMessage.FORMAT_ERROR;
import static christmas.util.content.ErrorMessage.RESERVATION_DATE_ERROR;
import static christmas.util.content.InformationMessage.MENU_CATEGORY_DRINK;

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
        boolean hasOnlyDrink = true;
        int totalQuantity = 0;
        for (String menu : order) {
            String[] menuDetail = menu.split("-");
            String menuName = menuDetail[0].trim();
            String quantity = menuDetail[1].trim();
            int menuQuantity = isQuantity(quantity);
            isFormat(menuDetail);
            isQuantity(quantity);
            isMenu(menuName);
            isUnique(uniqueMenus, menuName);
            totalQuantity += menuQuantity;
            if (!checkCategory(menuName)) {
                hasOnlyDrink = false;
            }
        }
        if (hasOnlyDrink) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }
        checkLimit(totalQuantity);
    }

    private static boolean checkCategory(String menuName) {
        String category = getCategory(menuName);
        return category.equals(MENU_CATEGORY_DRINK.getContent());
    }

    private static String getCategory(String menuName) {
        for (RestaurantMenu menu : RestaurantMenu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getCategory();
            }
        }
        throw new IllegalArgumentException(FORMAT_ERROR.getContent());
    }

    private static void checkLimit(int totalQauntity) {
        if (totalQauntity > 20) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
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

    private static int isQuantity(String quantity) {
        if (!quantity.matches("-?\\d+")) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }

        if (quantity.equals("0")) {
            throw new IllegalArgumentException(FORMAT_ERROR.getContent());
        }
        return Integer.parseInt(quantity);
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
