package com.bus.tian.tianbus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hsg on 2016/10/30.
 */

public class DateUtil {
    private final static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        String strDate = null;
        if (date != null) {
            strDate = dateFormatter.format(date);
        }
        return strDate;
    }

    public static String formatDateTime(Date date) {
        String strDate = null;
        if (date != null) {
            strDate = dateTimeFormatter.format(date);
        }
        return strDate;
    }

}
