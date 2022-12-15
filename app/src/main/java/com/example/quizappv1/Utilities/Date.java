package com.example.quizappv1.Utilities;

import java.text.SimpleDateFormat;

public class Date {

    public static String formatTime(long millis) {
        long s = millis % 60;
        long m = (millis / 60) % 60;
     //   long h = (millis / (60 * 60)) % 24;
        return String.format("%02d:%02d", m, s);
    }

    public static String getDate(long date) {
        String pattern = "M/d/yy, h:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

}
