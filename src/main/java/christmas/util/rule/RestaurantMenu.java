package christmas.util.rule;

public enum RestaurantMenu {

    APPETIZER_YANGSONGISOUP("양송이수프", 6000, "APPETIZER"),
    APPETIZER_TAPAS("타파스", 5500, "APPETIZER"),
    APPETIZER_CAESAR_SALAD("시저샐러드", 8000, "APPETIZER"),

    MAIN_T_BONE_STEAK("티본스테이크", 55000, "MAIN"),
    MAIN_BBQ_RIB("바비큐립", 54000, "MAIN"),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35000, "MAIN"),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25000, "MAIN"),

    DESSERT_CHOCO_CAKE("초코케이크", 15000, "DESSERT"),
    DESSERT_ICE_CREAM("아이스크림", 5000, "DESSERT"),

    DRINK_ZERO_COLA("제로콜라", 3000, "DRINK"),
    DRINK_RED_WINE("레드와인", 60000, "DRINK"),
    DRINK_CHAMPAGNE("샴페인", 25000, "DRINK");

    private final String name;
    private final int price;
    private final String category;

    RestaurantMenu(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
