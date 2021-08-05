package com.android.trovi.AutoUpdates;

import android.os.Environment;

import com.android.trovi.Utils.Globals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MeetingsUpdates {
    public static boolean MEET_STATUS = false;

    public void readA(){

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/volumeCollect.txt");

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

                if (numLine>4){
                    MEET_STATUS = true;

                    writeA();
                }

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void writeA(){

        int mA = 0;
        int mB = 0;

        final File fileAa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/volumeMedia.txt");
        final File fileAb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/volumeMediana.txt");
        final File fileAc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/volumeModa.txt");

        final File fileBa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/brightMedia.txt");
        final File fileBb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/brightMediana.txt");
        final File fileBc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/brightModa.txt");

        final File fileCa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Meetings/ringModa.txt");

        try {
            BufferedReader brAa = new BufferedReader(new FileReader(fileAa));
            BufferedReader brAb = new BufferedReader(new FileReader(fileAb));
            BufferedReader brAc = new BufferedReader(new FileReader(fileAc));
            mA += (int) Integer.parseInt(brAa.readLine());
            mA += (int) Integer.parseInt(brAb.readLine());
            mA += (int) Integer.parseInt(brAc.readLine());
            mA = mA/3;

            BufferedReader brBa = new BufferedReader(new FileReader(fileBa));
            BufferedReader brBb = new BufferedReader(new FileReader(fileBb));
            BufferedReader brBc = new BufferedReader(new FileReader(fileBc));
            mB += (int) Integer.parseInt(brBa.readLine());
            mB += (int) Integer.parseInt(brBb.readLine());
            mB += (int) Integer.parseInt(brBc.readLine());
            mB = mB/3;

            BufferedReader brCa = new BufferedReader(new FileReader(fileCa));

            Globals.ADB_VOLUME = Integer.toString(mA);
            Globals.ADB_BRIGHT = Integer.toString(mB);
            Globals.ADB_RING   = brCa.readLine();

            makeChanges();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void makeChanges(){
        int metrics = 0;
        int volTotal = 0;
        int briTotal = 0;
        int rmoTotal = 0;

        int volume = ((int) volTotal/metrics);
        int bright = ((int) briTotal/metrics);
        int ringmo = ((int) rmoTotal/metrics);

        Globals.METRIC_BRIGHT = bright;
        Globals.METRIC_VOLUME = volume;
        Globals.METRIC_RING   = ringmo;


    }
}
