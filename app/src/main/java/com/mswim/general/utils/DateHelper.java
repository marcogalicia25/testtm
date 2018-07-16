package com.mswim.general.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    private static final String outputPattern = "yyyy-MM-dd";

    public static String parseDateTo(String time) {

        if (time != null && !time.isEmpty()) {
            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());
            Date date = null;
            try {
                date = inputFormat.parse(time);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return time;
    }
}
