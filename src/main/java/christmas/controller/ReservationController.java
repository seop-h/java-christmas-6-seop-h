package christmas.controller;

import christmas.controller.util.Conversion;
import christmas.ui.input.view.InputView;

public class ReservationController {

    public int initDate() {
        String date = InputView.readDate();
        return Conversion.toInt(date);
    }

}
