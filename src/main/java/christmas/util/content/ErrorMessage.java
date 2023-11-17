package christmas.util.content;

public enum ErrorMessage {

    ERROR_WORD("[ERROR] "),
    RESERVATION_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    FORMAT_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    REPO_RESERVATION_ERROR("예약된 날짜가 없습니다."),
    REPO_MENU_ERROR("예약된 메뉴가 없습니다.");

    private final String content;

    ErrorMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
