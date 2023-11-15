package christmas.util;

import java.text.DecimalFormat;

public class AmountFormatter {

    public static String format(int input) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(input);
    }

}
