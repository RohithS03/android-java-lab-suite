package com.mobile.lab.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Date formatting helper for Android UI views.
 */
public class DateUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static String getTodayFormatted() {
        return sdf.format(new Date());
    }

    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return false;
        try {
            sdf.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
