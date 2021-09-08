package com.android.trovi.BackgroundCollect;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class MeetingsCollect {
    public static  Set<String> nameOfEvent = new LinkedHashSet<>();
    public static ArrayList<String> nameOfEvent2 = new ArrayList<String>();
    public static ArrayList<String> startDates = new ArrayList<String>();
    public static ArrayList<String> endDates = new ArrayList<String>();
    public static ArrayList<String> descriptions = new ArrayList<String>();

    public static Set<String> readCalendarEvent(Context context) {
        Cursor cursor = context.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        new String[]{"calendar_id", "title", "description",
                                "dtstart", "dtend", "eventLocation"}, null,
                        null, null);
        cursor.moveToFirst();
        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        // fetching calendars id
        nameOfEvent.clear();
        startDates.clear();
        endDates.clear();
        descriptions.clear();

        Date date = new Date();
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day2         = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
        String year         = (String) DateFormat.format("yyyy", date); // 2013

        //Data Atual
        String meuDia = day2 + "/" + monthNumber + "/" + year;

        for (int i = 0; i < CNames.length; i++) {

            String[] corte = getDate(Long.parseLong(cursor.getString(3))).split(" ");

            if (corte[0].equals(meuDia)){

                nameOfEvent.add(cursor.getString(1)+":@:"+
                        getDate(Long.parseLong(cursor.getString(3))));
                //startDates.add(getDate(Long.parseLong(cursor.getString(3))));
                //endDates.add(getDate(Long.parseLong(cursor.getString(4))));
                //descriptions.add(cursor.getString(2));
                CNames[i] = cursor.getString(1);
            }
            cursor.moveToNext();

        }
        return nameOfEvent;
    }

    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
