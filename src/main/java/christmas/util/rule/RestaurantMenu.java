package christmas.util.rule;

public enum RestaurantMenu {

    APPETIZER_YANGSONGISOUP("양송이수프", 6000),
    APPETIZER_TAPAS("타파스", 5500),
    APPETIZER_CAESAR_SALAD("시저샐러드", 8000),

    MAIN_T_BONE_STEAK("티본스테이크", 55000),
    MAIN_BBQ_RIB("바비큐립", 54000),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35000),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25000),

    DESSERT_CHOCO_CAKE("초코케이크", 15000),
    DESSERT_ICE_CREAM("아이스크림", 5000),

    DRINK_ZERO_COLA("제로콜라", 3000),
    DRINK_RED_WINE("레드와인", 60000),
    DRINK_CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    RestaurantMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}
