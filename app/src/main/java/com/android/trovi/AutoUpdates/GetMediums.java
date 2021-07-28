package com.android.trovi.AutoUpdates;

import android.os.Environment;
import android.util.Log;

import com.android.trovi.Utils.Globals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import static com.android.trovi.AutoUpdates.CheckMetrics.TOTAL_METRICS;

public class GetMediums {

    public void init(){

        if (TOTAL_METRICS>2){
            readA();
            readB();
            readC();
            readD();
            readE();

            UpdatePhone up = new UpdatePhone();
            up.makeChanges();
        }
    }

    public void readA(){

        int mA = 0;
        int mB = 0;
        int mC = 0;

        String completePathAa = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/volumeMedia.txt";
        String completePathAb = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/volumeMediana.txt";
        String completePathAc = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/volumeModa.txt";

        String completePathBa = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/brightMedia.txt";
        String completePathBb = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/brightMediana.txt";
        String completePathBc = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/brightModa.txt";

        String completePathCa = "adb."+ Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/ringModa.txt";

        FileInputStream is;
        BufferedReader reader;

        final File fileAa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/ADB/"+completePathAa);
        final File fileAb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/ADB/"+completePathAb);
        final File fileAc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/ADB/"+completePathAc);

        final File fileBa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/ADB/"+completePathBa);
        final File fileBb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/ADB/"+completePathBb);
        final File fileBc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/ADB/"+completePathBc);

        final File fileCa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/ADB/"+completePathCa);

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
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readB(){

        int mA = 0;
        int mB = 0;
        int mC = 0;

        String completePathAa = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/volumeMedia.txt";
        String completePathAb = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/volumeMediana.txt";
        String completePathAc = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/volumeModa.txt";

        String completePathBa = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/brightMedia.txt";
        String completePathBb = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/brightMediana.txt";
        String completePathBc = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/brightModa.txt";

        String completePathCa = "bdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_BATTERY+"/ringModa.txt";

        FileInputStream is;
        BufferedReader reader;

        final File fileAa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/BDB/"+completePathAa);
        final File fileAb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/BDB/"+completePathAb);
        final File fileAc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/BDB/"+completePathAc);

        final File fileBa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/BDB/"+completePathBa);
        final File fileBb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/BDB/"+completePathBb);
        final File fileBc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/BDB/"+completePathBc);

        final File fileCa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/BDB/"+completePathCa);

        try {
            BufferedReader brAa = new BufferedReader(new FileReader(fileAa));
            BufferedReader brAb = new BufferedReader(new FileReader(fileAb));
            BufferedReader brAc = new BufferedReader(new FileReader(fileAc));
            mA += Integer.parseInt(brAa.readLine());
            mA += Integer.parseInt(brAb.readLine());
            mA += Integer.parseInt(brAc.readLine());
            mA = mA/3;

            BufferedReader brBa = new BufferedReader(new FileReader(fileBa));
            BufferedReader brBb = new BufferedReader(new FileReader(fileBb));
            BufferedReader brBc = new BufferedReader(new FileReader(fileBc));
            mB += Integer.parseInt(brBa.readLine());
            mB += Integer.parseInt(brBb.readLine());
            mB += Integer.parseInt(brBc.readLine());
            mB = mB/3;

            BufferedReader brCa = new BufferedReader(new FileReader(fileCa));

            Globals.BDB_VOLUME = Integer.toString(mA);
            Globals.BDB_BRIGHT = Integer.toString(mB);
            Globals.BDB_RING   = brCa.readLine();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readC(){

        int mA = 0;
        int mB = 0;
        int mC = 0;

        String completePathAa = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/volumeMedia.txt";
        String completePathAb = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/volumeMediana.txt";
        String completePathAc = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/volumeModa.txt";

        String completePathBa = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/brightMedia.txt";
        String completePathBb = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/brightMediana.txt";
        String completePathBc = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/brightModa.txt";

        String completePathCa = "cdb."+ Globals.LINE_LOCATION+"."+Globals.LINE_CONNECTION+"/ringModa.txt";

        FileInputStream is;
        BufferedReader reader;

        final File fileAa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/CDB/"+completePathAa);
        final File fileAb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/CDB/"+completePathAb);
        final File fileAc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/CDB/"+completePathAc);

        final File fileBa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/CDB/"+completePathBa);
        final File fileBb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/CDB/"+completePathBb);
        final File fileBc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/CDB/"+completePathBc);

        final File fileCa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/CDB/"+completePathCa);

        try {
            BufferedReader brAa = new BufferedReader(new FileReader(fileAa));
            BufferedReader brAb = new BufferedReader(new FileReader(fileAb));
            BufferedReader brAc = new BufferedReader(new FileReader(fileAc));
            mA += Integer.parseInt(brAa.readLine());
            mA += Integer.parseInt(brAb.readLine());
            mA += Integer.parseInt(brAc.readLine());
            mA = mA/3;

            BufferedReader brBa = new BufferedReader(new FileReader(fileBa));
            BufferedReader brBb = new BufferedReader(new FileReader(fileBb));
            BufferedReader brBc = new BufferedReader(new FileReader(fileBc));
            mB += Integer.parseInt(brBa.readLine());
            mB += Integer.parseInt(brBb.readLine());
            mB += Integer.parseInt(brBc.readLine());
            mB = mB/3;

            BufferedReader brCa = new BufferedReader(new FileReader(fileCa));

            Globals.CDB_VOLUME = Integer.toString(mA);
            Globals.CDB_BRIGHT = Integer.toString(mB);
            Globals.CDB_RING   = brCa.readLine();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readD(){

        int mA = 0;
        int mB = 0;
        int mC = 0;

        String completePathAa = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/volumeMedia.txt";
        String completePathAb = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/volumeMediana.txt";
        String completePathAc = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/volumeModa.txt";

        String completePathBa = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/brightMedia.txt";
        String completePathBb = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/brightMediana.txt";
        String completePathBc = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/brightModa.txt";

        String completePathCa = "ddb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALVOL+"/ringModa.txt";

        FileInputStream is;
        BufferedReader reader;

        final File fileAa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/DDB/"+completePathAa);
        final File fileAb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/DDB/"+completePathAb);
        final File fileAc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/DDB/"+completePathAc);

        final File fileBa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/DDB/"+completePathBa);
        final File fileBb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/DDB/"+completePathBb);
        final File fileBc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/DDB/"+completePathBc);

        final File fileCa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/DDB/"+completePathCa);

        try {
            BufferedReader brAa = new BufferedReader(new FileReader(fileAa));
            BufferedReader brAb = new BufferedReader(new FileReader(fileAb));
            BufferedReader brAc = new BufferedReader(new FileReader(fileAc));
            mA += Integer.parseInt(brAa.readLine());
            mA += Integer.parseInt(brAb.readLine());
            mA += Integer.parseInt(brAc.readLine());
            mA = mA/3;

            BufferedReader brBa = new BufferedReader(new FileReader(fileBa));
            BufferedReader brBb = new BufferedReader(new FileReader(fileBb));
            BufferedReader brBc = new BufferedReader(new FileReader(fileBc));
            mB += Integer.parseInt(brBa.readLine());
            mB += Integer.parseInt(brBb.readLine());
            mB += Integer.parseInt(brBc.readLine());
            mB = mB/3;

            BufferedReader brCa = new BufferedReader(new FileReader(fileCa));

            Globals.DDB_VOLUME = Integer.toString(mA);
            Globals.DDB_BRIGHT = Integer.toString(mB);
            Globals.DDB_RING   = brCa.readLine();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void readE(){

        int mA = 0;
        int mB = 0;
        int mC = 0;

        String completePathAa = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/volumeMedia.txt";
        String completePathAb = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/volumeMediana.txt";
        String completePathAc = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/volumeModa.txt";

        String completePathBa = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/brightMedia.txt";
        String completePathBb = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/brightMediana.txt";
        String completePathBc = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/brightModa.txt";

        String completePathCa = "edb."+ Globals.LINE_LOCATION+"."+Globals.LINE_LOCALBRI+"/ringModa.txt";

        FileInputStream is;
        BufferedReader reader;

        final File fileAa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/EDB/"+completePathAa);
        final File fileAb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/EDB/"+completePathAb);
        final File fileAc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/EDB/"+completePathAc);

        final File fileBa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/EDB/"+completePathBa);
        final File fileBb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/EDB/"+completePathBb);
        final File fileBc = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/EDB/"+completePathBc);

        final File fileCa = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Logs/EDB/"+completePathCa);

        try {
            BufferedReader brAa = new BufferedReader(new FileReader(fileAa));
            BufferedReader brAb = new BufferedReader(new FileReader(fileAb));
            BufferedReader brAc = new BufferedReader(new FileReader(fileAc));
            mA += Integer.parseInt(brAa.readLine());
            mA += Integer.parseInt(brAb.readLine());
            mA += Integer.parseInt(brAc.readLine());
            mA = mA/3;

            BufferedReader brBa = new BufferedReader(new FileReader(fileBa));
            BufferedReader brBb = new BufferedReader(new FileReader(fileBb));
            BufferedReader brBc = new BufferedReader(new FileReader(fileBc));
            mB += Integer.parseInt(brBa.readLine());
            mB += Integer.parseInt(brBb.readLine());
            mB += Integer.parseInt(brBc.readLine());
            mB = mB/3;

            BufferedReader brCa = new BufferedReader(new FileReader(fileCa));

            Globals.EDB_VOLUME = Integer.toString(mA);
            Globals.EDB_BRIGHT = Integer.toString(mB);
            Globals.EDB_RING   = brCa.readLine();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

}
