package com.android.trovi.BackgroundCollect;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

public class DateTimeCollect {

    public Calendar cal = Calendar.getInstance();

    public int hours = cal.get(Calendar.HOUR_OF_DAY);
    public int mins = cal.get(Calendar.MINUTE);
    public int secs = cal.get(Calendar.SECOND);

    public String getHour(){
        return Integer.toString(hours);
    }

    public String getMins(){
        return Integer.toString(mins);
    }

    public String getSecs(){
        return Integer.toString(mins);
    }

    public String LINE_TIME(){

        int minsCorrected;

        if (hours==0&&mins<30){
            minsCorrected = 0;
        }
        else if (hours==0&&mins>=30){
            minsCorrected = 1;
        }
        else if (hours==1&&mins<30){
            minsCorrected = 2;
        }
        else if (hours==1&&mins>=30){
            minsCorrected = 3;
        }
        else if (hours==2&&mins<30){
            minsCorrected = 4;
        }
        else if (hours==2&&mins>=30){
            minsCorrected = 5;
        }
        else if (hours==3&&mins<30){
            minsCorrected = 6;
        }
        else if (hours==3&&mins>=30){
            minsCorrected = 7;
        }
        else if (hours==4&&mins<30){
            minsCorrected = 8;
        }
        else if (hours==4&&mins>=30){
            minsCorrected = 9;
        }
        else if (hours==5&&mins<30){
            minsCorrected = 10;
        }
        else if (hours==5&&mins>=30){
            minsCorrected = 11;
        }
        else if (hours==6&&mins<30){
            minsCorrected = 12;
        }
        else if (hours==6&&mins>=30){
            minsCorrected = 13;
        }
        else if (hours==7&&mins<30){
            minsCorrected = 14;
        }
        else if (hours==7&&mins>=30){
            minsCorrected = 15;
        }
        else if (hours==8&&mins<30){
            minsCorrected = 16;
        }
        else if (hours==8&&mins>=30){
            minsCorrected = 17;
        }
        else if (hours==9&&mins<30){
            minsCorrected = 18;
        }
        else if (hours==9&&mins>=30){
            minsCorrected = 19;
        }
        else if (hours==10&&mins<30){
            minsCorrected = 20;
        }
        else if (hours==10&&mins>=30){
            minsCorrected = 21;
        }
        else if (hours==11&&mins<30){
            minsCorrected = 22;
        }
        else if (hours==11&&mins>=30){
            minsCorrected = 23;
        }
        else if (hours==12&&mins<30){
            minsCorrected = 24;
        }
        else if (hours==12&&mins>=30){
            minsCorrected = 25;
        }
        else if (hours==13&&mins<30){
            minsCorrected = 26;
        }
        else if (hours==13&&mins>=30){
            minsCorrected = 27;
        }
        else if (hours==14&&mins<30){
            minsCorrected = 28;
        }
        else if (hours==14&&mins>=30){
            minsCorrected = 29;
        }
        else if (hours==15&&mins<30){
            minsCorrected = 30;
        }
        else if (hours==15&&mins>=30){
            minsCorrected = 31;
        }
        else if (hours==16&&mins<30){
            minsCorrected = 32;
        }
        else if (hours==16&&mins>=30){
            minsCorrected = 33;
        }
        else if (hours==17&&mins<30){
            minsCorrected = 34;
        }
        else if (hours==17&&mins>=30){
            minsCorrected = 35;
        }
        else if (hours==18&&mins<30){
            minsCorrected = 36;
        }
        else if (hours==18&&mins>=30){
            minsCorrected = 37;
        }
        else if (hours==19&&mins<30){
            minsCorrected = 38;
        }
        else if (hours==19&&mins>=30){
            minsCorrected = 39;
        }
        else if (hours==20&&mins<30){
            minsCorrected = 40;
        }
        else if (hours==20&&mins>=30){
            minsCorrected = 41;
        }
        else if (hours==21&&mins<30){
            minsCorrected = 42;
        }
        else if (hours==21&&mins>=30){
            minsCorrected = 43;
        }
        else if (hours==22&&mins<30){
            minsCorrected = 44;
        }
        else if (hours==22&&mins>=30){
            minsCorrected = 45;
        }
        else if (hours==23&&mins<30){
            minsCorrected = 46;
        }
        else{
            minsCorrected = 47;
        }


        return Integer.toString(minsCorrected);

    }
}
