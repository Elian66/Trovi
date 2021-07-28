package com.android.trovi.ScreenData;

import android.os.Handler;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

public class UserAndDateInfo {

    public String hour_str,mins_str;
    public String day_str;

    public Date date = new Date();
    public Calendar cal = Calendar.getInstance();

    public int hours = cal.get(Calendar.HOUR_OF_DAY);
    public int mins = cal.get(Calendar.MINUTE);
    public final int secs = cal.get(Calendar.SECOND);

    public String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
    public String dayNumber    = (String) DateFormat.format("dd",   date); // 20
    public String monthString  = (String) DateFormat.format("MMM",  date); // Jun
    public String monthNumber  = (String) DateFormat.format("MM",   date); // 06
    public String year         = (String) DateFormat.format("yyyy", date); // 2013

    public void configs(){
        checkHour();
        checkDate();
    }

    public String checkHour(){
        //Hours
        if (hours<10){
            hour_str = "0"+Integer.toString(hours);
        }else {
            hour_str = Integer.toString(hours);
        }

        //Minutes
        if (mins<10){
            mins_str = "0"+Integer.toString(mins);
        }else {
            mins_str = Integer.toString(mins);
        }

        return hour_str+":"+mins_str;
    }

    public String checkDate(){
        //Hours
        if (Integer.parseInt(dayNumber)<10){
            day_str = "0"+dayNumber;
        }else {
            day_str = dayNumber;
        }

        return day_str+" de "+monthString;
    }

}
