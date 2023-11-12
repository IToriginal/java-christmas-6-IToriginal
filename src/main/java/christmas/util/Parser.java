package christmas.util;

import static christmas.util.content.ErrorMessage.ERROR_WORD;
import static christmas.util.content.ErrorMessage.FORMAT_ERROR;
import static christmas.util.content.InformationMessage.WON;

import christmas.util.rule.RestaurantMenu;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Parser {

    public static LocalDate parseVisitDate(String visitDate) {
        int day = Integer.parseInt(visitDate);
        LocalDate now = LocalDate.now();
        return LocalDate.of(now.getYear(), 12, day);
    }

    public static HashMap<String, Integer> parseOrderInfo(String orderInfo) {
        HashMap<String, Integer> menu = new LinkedHashMap<>();
        String[] orders = orderInfo.split(",");
        for (String order : orders) {
            String[] menuItem = order.split("-");
            if (menuItem.length == 2) {
                menu.put(menuItem[0], Integer.parseInt(menuItem[1]));
            }
        }
        return menu;
    }

    public static String formatCurrency(Integer amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        String formattedAmount = currencyFormat.format(amount);
        return formattedAmount.replace(currencyFormat.getCurrency().getSymbol(), "")
                + WON.getContent();
    }

    public static HashMap<String, Integer> parseCategoryCount(HashMap<String, Integer> order) {
        HashMap<String, Integer> orderCategory = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String category = getCategory(entry.getKey());
            Integer quantity = entry.getValue();

            orderCategory.computeIfPresent(category,
                    (key, existingQuantity) -> existingQuantity + quantity);
            orderCategory.computeIfAbsent(category, key -> quantity);
        }
        return orderCategory;
    }

    private static String getCategory(String name) {
        for (RestaurantMenu menu : RestaurantMenu.values()) {
            if (menu.getName().equals(name)) {
                return menu.getCategory();
            }
        }
        throw new IllegalArgumentException(ERROR_WORD.getContent() + FORMAT_ERROR.getContent());
    }

}
