package christmas.util;

import static christmas.util.content.ErrorMessage.ERROR_WORD;
import static christmas.util.content.ErrorMessage.FORMAT_ERROR;
import static christmas.util.content.InformationMessage.WON;

import christmas.util.rule.RestaurantMenu;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
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
        return Arrays.stream(orderInfo.split(","))
                .map(order -> order.split("-"))
                .filter(menuItem -> menuItem.length == 2)
                .collect(
                        LinkedHashMap::new,
                        (result, menuItem) -> result.put(menuItem[0], Integer.parseInt(menuItem[1])),
                        HashMap::putAll
                );
    }

    public static String formatCurrency(Integer amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        String formattedAmount = currencyFormat.format(amount);
        return formattedAmount.replace(currencyFormat.getCurrency().getSymbol(), "")
                + WON.getContent();
    }

    public static HashMap<String, Integer> parseCategoryCount(HashMap<String, Integer> order) {
        return order.entrySet().stream()
                .collect(
                        HashMap::new,
                        (result, entry) -> {
                            String category = getCategory(entry.getKey());
                            Integer quantity = entry.getValue();
                            result.merge(category, quantity, Integer::sum);
                        },
                        HashMap::putAll
                );
    }

    private static String getCategory(String name) {
        return Arrays.stream(RestaurantMenu.values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .map(RestaurantMenu::getCategory)
                .orElseThrow(() -> new IllegalArgumentException(
                        ERROR_WORD.getContent() + FORMAT_ERROR.getContent()));
    }

}
