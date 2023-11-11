package christmas.domain.constant;

import java.util.List;

public abstract class DateConst {

    public static final int FIRST_DAY = 1;
    public static final int LAST_DAY = 31;

    public static final int CHRISTMAS = 25;
    public static final List<Integer> WEEKENDS = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    public static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);

}
