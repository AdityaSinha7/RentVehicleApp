package com.example.utility;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class CoreUtils {

    public static List<String> split(String inputString, String delimiter) {
        if (inputString != null && delimiter != null) {
            String[] split = inputString.split(delimiter);
            return Arrays.asList(split);
        }
        return null;
    }

    public static boolean isStringEmpty(String inputString) {
        return inputString == null || inputString == "";
    }
}
