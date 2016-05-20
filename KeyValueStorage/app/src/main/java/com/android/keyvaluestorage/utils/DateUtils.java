package com.android.keyvaluestorage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtils {

    private static List<SimpleDateFormat> knownPatterns = new ArrayList<>();

    private static void init() {
        knownPatterns.add(new SimpleDateFormat("EEEE MMM dd hh:mm:ss z yyyy", Locale.getDefault()));
    }

    public static Date getDateTime(String convertDate) {
        init();
        Date convertedDate = null;
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                convertedDate = pattern.parse(convertDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return convertedDate;
    }

    public static String getDisplayDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        return formatter.format(date);
    }
}
