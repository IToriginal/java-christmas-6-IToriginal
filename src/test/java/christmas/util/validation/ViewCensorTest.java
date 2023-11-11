package christmas.util.validation;

import static christmas.util.validation.ViewCensor.validateOrderMenu;
import static christmas.util.validation.ViewCensor.validateReservationDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ViewCensorTest {

    @DisplayName("정수가 아닌 입력값으로 예약하면 예외가 발생한다.")
    @Test
    void validReservationNumericTest() {
        String inputMinus = "-25";
        String inputDouble = "1.0";
        assertThrows(IllegalArgumentException.class, () -> validateReservationDate(inputMinus));
        assertThrows(IllegalArgumentException.class, () -> validateReservationDate(inputDouble));
    }


    @DisplayName("한글로 예약하면 예외가 발생한다.")
    @Test
    void validReservationKoreanTest() {
        String input = "이십육일";
        assertThrows(IllegalArgumentException.class, () -> validateReservationDate(input));
    }

    @DisplayName("영어로 예약하면 예외가 발생한다.")
    @Test
    void validReservationEnglishTest() {
        String input = "one";
        assertThrows(IllegalArgumentException.class, () -> validateReservationDate(input));
    }

    @DisplayName("입력값을 작성하지 않고 예약하면 예외가 발생한다.")
    @Test
    void validReservationSkipTest() {
        String inputBlank = "";
        String inputSpacebar = " ";
        assertThrows(IllegalArgumentException.class, () -> validateReservationDate(inputBlank));
        assertThrows(IllegalArgumentException.class, () -> validateReservationDate(inputSpacebar));
    }

    @DisplayName("메뉴에 없는 메뉴를 입력하면 예외가 발생한다.")
    @Test
    void validateOrderMenuMethodNotMenuTest() {
        String input = "라면-1";
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(input));
    }

    @DisplayName("메뉴의 개수는 최소 1개이며 0을 입력하면 예외가 발생한다.")
    @Test
    void validateOrderMenuMethodQuantityTest() {
        String input = "양송이수프-0";
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(input));
    }

    @DisplayName("메뉴의 형식에 맞지 않게 입력하면, 예외가 발생한다.")
    @Test
    void validateOrderMenuMethodFormatTest() {
        String inputReverse = "0-양송이수프";
        String inputSpace = "0 - 양송이수프";
        String inputNotDividing = "양송이수프1";
        String inputOnlyMenuName = "양송이수프";
        String inputOnlyQuantity = "0";
        String inputBlank = "";
        String inputSpacebar = " ";

        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(inputReverse));
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(inputSpace));
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(inputNotDividing));
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(inputOnlyMenuName));
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(inputOnlyQuantity));
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(inputBlank));
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(inputSpacebar));
    }

    @DisplayName("메뉴에 중복된 이름이 입력되면, 예외가 발생한다..")
    @Test
    void validateOrderMenuMethodUniqueTest() {
        String input = "양송이수프-1,제로콜라-1,양송이수프-1";
        assertThrows(IllegalArgumentException.class, () -> validateOrderMenu(input));
    }

}