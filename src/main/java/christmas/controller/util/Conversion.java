package christmas.controller.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.constant.ErrorMessage.NOT_INVALID_DATE;
import static christmas.constant.ErrorMessage.NOT_INVALID_ORDER;

public class Conversion {

    public static int toInt(String input) {
        try {
            int parseInt = Integer.parseInt(input);
            return parseInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INVALID_DATE, e);
        }
    }

    public static Map<String, Integer> toMap(String input) {
        Map<String, Integer> result = new LinkedHashMap<>();

        try {
            storeEachElementInMap(input, result);
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INVALID_ORDER, e);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private static void storeEachElementInMap(String input, Map<String, Integer> result) {
        Arrays.stream(input.split(","))
                .map(menuAndServing -> menuAndServing.split("-"))
                .peek(anOrder -> {
                    if (anOrder.length != 2 || result.containsKey(anOrder[0])) {
                        throw new IllegalArgumentException(NOT_INVALID_ORDER);
                    }
                })
                .forEach(anOrder ->
                        result.put(anOrder[0], Integer.parseInt(anOrder[1]))
                );
    }

}
