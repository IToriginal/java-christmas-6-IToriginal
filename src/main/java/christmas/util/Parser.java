package christmas.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

}
