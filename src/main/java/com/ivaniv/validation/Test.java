package com.ivaniv.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by iivaniv on 21.10.2016.
 */
public class Test {

    public static void main( String [] args ){


        Calendar cal  = new GregorianCalendar();
//        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 1600);

        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(cal.get(Calendar.HOUR));
        System.out.println( cal.get(Calendar.MINUTE));
        System.out.println( cal.get(Calendar.SECOND));

        Date calDate = cal.getTime();
        System.out.println("CalDate : "+calDate);

        String dateStr = "18:35:29";
        DateFormat readFormat = new SimpleDateFormat( "HH:mm:ss");
        DateFormat writeFormat = new SimpleDateFormat( "HH:mm:ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String formattedDate = "";
        if (date != null) {
            formattedDate = writeFormat.format(date);
        }

        System.out.println("Date : "+date.toString());
    }
}
