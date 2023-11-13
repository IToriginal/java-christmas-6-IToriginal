package christmas.util.content;

public enum InformationMessage {

    GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제 인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW_BENEFITS("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    CHECK_MENU("<주문 메뉴>"),
    CHECK_LIST("%s %d개%n"),
    TOTAL_ORDER_AMOUNT("\n<할인 전 총주문 금액>"),
    WON("원"),
    FREEBIES_MENU("\n<증정 메뉴>"),
    NOTHING("없음"),
    BENEFITS_LIST("\n<혜택 내역>"),
    SEPARATION_MINUS(": -"),
    FURE_MINUS("-"),
    DATE_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    FREEBIES_EVENT("증정 이벤트"),
    BENEFIT_AMOUNT("\n<총혜택 금액>"),
    FINAL_PAYMENT("\n<할인 후 예상 결제 금액>"),
    EVENT_BADGE("\n<12월 이벤트 배지>"),
    BADGE_STAR("별"),
    BADGE_TREE("트리"),
    BADGE_SANTA("산타"),
    MENU_CATEGORY_APPETIZER("APPETIZER"),
    MENU_CATEGORY_MAIN("MAIN"),
    MENU_CATEGORY_DESSERT("DESSERT"),
    MENU_CATEGORY_DRINK("DRINK");

    private final String content;

    InformationMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
