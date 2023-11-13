package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;


public class Planner {

    private Long id; // 식별자 (현재 요구사항에는 사용되지 않음)
    private LocalDate reservationDate; // 예약 날짜
    private HashMap<String, Integer> menu; // 메뉴 (이름, 가격)
    private Integer totalOrderAmount; // 총 주문 금액
    private HashMap<String, Integer> benefits; // 할인 혜택 리스트 (혜택 내용, 할인 가격)
    private Integer benefitsAmount; // 총 할인 금액
    private String eventBadge; // 이벤트 뱃지

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public HashMap<String, Integer> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<String, Integer> menu) {
        this.menu = menu;
    }

    public Integer getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(Integer totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public HashMap<String, Integer> getBenefits() {
        return benefits;
    }

    public void setBenefits(HashMap<String, Integer> benefits) {
        this.benefits = benefits;
    }

    public Integer getBenefitsAmount() {
        return benefitsAmount;
    }

    public void setBenefitsAmount(Integer benfitsAmount) {
        this.benefitsAmount = benfitsAmount;
    }

    public String getEventBadge() {
        return eventBadge;
    }

    public void setEventBadge(String eventBadge) {
        this.eventBadge = eventBadge;
    }

}
