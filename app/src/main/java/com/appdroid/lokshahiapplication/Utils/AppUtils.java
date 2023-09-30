package com.appdroid.lokshahiapplication.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtils {

    public static Date getFormattedDate(String oldDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(oldDate);


            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
