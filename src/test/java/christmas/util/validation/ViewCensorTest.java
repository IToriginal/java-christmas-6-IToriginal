package christmas.util.validation;

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

}