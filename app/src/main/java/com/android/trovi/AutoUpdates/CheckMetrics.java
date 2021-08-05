package com.android.trovi.AutoUpdates;

import android.os.Environment;
import android.util.Log;

import com.android.trovi.Utils.Globals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class CheckMetrics {

    public static boolean ADB_STATUS = false;
    public static boolean BDB_STATUS = false;
    public static boolean CDB_STATUS = false;
    public static boolean DDB_STATUS = false;
    public static boolean EDB_STATUS = false;
    public static boolean FDB_STATUS = false;
    public static boolean GDB_STATUS = false;
    public static boolean HDB_STATUS = false;
    public static int TOTAL_METRICS = 0;

    public void init(){
        readA();
        readB();
        readC();
        readD();
        readE();
        readF();
        readG();
        readH();

        GetMediums gm = new GetMediums();
        gm.init();

    }

    public void readA(){
        String completePathA = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/ADB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    ADB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readA();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readB(){
        String completePathA = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/BDB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    BDB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readB();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readC(){
        String completePathA = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/CDB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    CDB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readC();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readD(){
        String completePathA = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/DDB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    DDB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readD();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readE(){
        String completePathA = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/EDB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    EDB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readE();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readF(){
        String completePathA = "fdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_SAVING+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/FDB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    FDB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readF();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readG(){
        String completePathA = "gdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BLUETOOTH+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/GDB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    GDB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readG();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readH(){
        String completePathA = "hdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_HEADPHONE+"/volumeCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/HDB/"+completePathA);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    numLine++;
                    line = reader.readLine();
                }

                if (numLine>39){
                    HDB_STATUS = true;
                    TOTAL_METRICS++;

                    GetMediums gm = new GetMediums();
                    gm.readH();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
