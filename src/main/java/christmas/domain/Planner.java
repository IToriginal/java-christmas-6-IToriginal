package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;


public class Planner {

    private Long id; // 식별자 (현재 요구사항에는 사용되지 않음)
    private LocalDate reservationDate; // 예약 날짜
    private HashMap<String, Integer> menu; // 메뉴 (이름, 가격)
    private Integer totalOrderAmount; // 총 주문 금액

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
}
