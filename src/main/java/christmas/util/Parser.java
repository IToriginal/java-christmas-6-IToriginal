package christmas.util;

import static christmas.util.content.InformationMessage.WON;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

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

}
