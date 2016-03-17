package com.example.administrator.mygift.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 16-3-15.
 */
public class DateFormatTool {
    public static String formatDate(long time) {
        return formatDate(time, "yyyy-MM-dd E");
    }

    public static String formatDate(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(time));
    }
}
