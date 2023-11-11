package christmas.util.content;

public enum InformationMessage {

    GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제 인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW_BENEFITS("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    CHECK_MENU("<주문 메뉴>"),
    CHECK_ORDER_LIST("%s %d개%n");

    private final String content;

    InformationMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
