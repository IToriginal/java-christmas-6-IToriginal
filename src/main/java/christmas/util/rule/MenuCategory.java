package christmas.util.rule;

public enum MenuCategory {

    MENU_CATEGORY_APPETIZER("APPETIZER"),
    MENU_CATEGORY_MAIN("MAIN"),
    MENU_CATEGORY_DESSERT("DESSERT"),
    MENU_CATEGORY_DRINK("DRINK");

    private final String content;

    MenuCategory(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
