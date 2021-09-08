package com.android.trovi.AutoUpdates;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.trovi.Services.BackgroundService;
import com.android.trovi.Utils.Globals;

public class UpdatePhone {

    String importance_time,importance_battery,importance_connection,importance_saving;
    String importance_localbright,importance_localvolume,importance_bluetooth,importance_headphone;

    private Context mContext;

    public void makeChanges(){
        int metrics = 0;
        int volTotal = 0;
        int briTotal = 0;
        int rmoTotal = 0;

        this.mContext = mContext.getApplicationContext();

        SharedPreferences prefs = mContext.getSharedPreferences("prefs", MODE_PRIVATE);

        importance_time = prefs.getString("importance_time","");
        importance_battery = prefs.getString("importance_battery","");
        importance_connection = prefs.getString("importance_connection","");
        importance_saving = prefs.getString("importance_saving","");
        importance_localbright = prefs.getString("importance_localbright","");
        importance_localvolume = prefs.getString("importance_localvolume","");
        importance_bluetooth = prefs.getString("importance_bluetooth","");
        importance_headphone = prefs.getString("importance_headphone","");

        if (CheckMetrics.ADB_STATUS){
            metrics += Integer.parseInt(importance_time);
            volTotal += Integer.parseInt(Globals.ADB_VOLUME)*Integer.parseInt(importance_time);
            briTotal += Integer.parseInt(Globals.ADB_BRIGHT)*Integer.parseInt(importance_time);
            rmoTotal += Integer.parseInt(Globals.ADB_RING)*Integer.parseInt(importance_time);
        }

        if (CheckMetrics.BDB_STATUS){
            metrics += Integer.parseInt(importance_battery);
            volTotal += Integer.parseInt(Globals.BDB_VOLUME)*Integer.parseInt(importance_battery);
            briTotal += Integer.parseInt(Globals.BDB_BRIGHT)*Integer.parseInt(importance_battery);
            rmoTotal += Integer.parseInt(Globals.BDB_RING)*Integer.parseInt(importance_battery);
        }

        if (CheckMetrics.CDB_STATUS){
            metrics += Integer.parseInt(importance_connection);
            volTotal += Integer.parseInt(Globals.CDB_VOLUME)*Integer.parseInt(importance_connection);
            briTotal += Integer.parseInt(Globals.CDB_BRIGHT)*Integer.parseInt(importance_connection);
            rmoTotal += Integer.parseInt(Globals.CDB_RING)*Integer.parseInt(importance_connection);
        }

        if (CheckMetrics.DDB_STATUS){
            metrics += Integer.parseInt(importance_localvolume);
            volTotal += Integer.parseInt(Globals.DDB_VOLUME)*Integer.parseInt(importance_localvolume);
            briTotal += Integer.parseInt(Globals.DDB_BRIGHT)*Integer.parseInt(importance_localvolume);
            rmoTotal += Integer.parseInt(Globals.DDB_RING)*Integer.parseInt(importance_localvolume);
        }

        if (CheckMetrics.EDB_STATUS){
            metrics += Integer.parseInt(importance_localbright);
            volTotal += Integer.parseInt(Globals.EDB_VOLUME)*Integer.parseInt(importance_localbright);
            briTotal += Integer.parseInt(Globals.EDB_BRIGHT)*Integer.parseInt(importance_localbright);
            rmoTotal += Integer.parseInt(Globals.EDB_RING)*Integer.parseInt(importance_localbright);
        }

        if (CheckMetrics.FDB_STATUS){
            metrics += Integer.parseInt(importance_saving);
            volTotal += Integer.parseInt(Globals.FDB_VOLUME)*Integer.parseInt(importance_saving);
            briTotal += Integer.parseInt(Globals.FDB_BRIGHT)*Integer.parseInt(importance_saving);
            rmoTotal += Integer.parseInt(Globals.FDB_RING)*Integer.parseInt(importance_saving);
        }

        if (CheckMetrics.GDB_STATUS){
            metrics += Integer.parseInt(importance_bluetooth);
            volTotal += Integer.parseInt(Globals.GDB_VOLUME)*Integer.parseInt(importance_bluetooth);
            briTotal += Integer.parseInt(Globals.GDB_BRIGHT)*Integer.parseInt(importance_bluetooth);
            rmoTotal += Integer.parseInt(Globals.GDB_RING)*Integer.parseInt(importance_bluetooth);
        }

        if (CheckMetrics.HDB_STATUS){
            metrics += Integer.parseInt(importance_headphone);
            volTotal += Integer.parseInt(Globals.GDB_VOLUME)*Integer.parseInt(importance_headphone);
            briTotal += Integer.parseInt(Globals.GDB_BRIGHT)*Integer.parseInt(importance_headphone);
            rmoTotal += Integer.parseInt(Globals.GDB_RING)*Integer.parseInt(importance_headphone);
        }

        if (CheckMetrics.TOTAL_METRICS>2){
            int volume = ((int) volTotal/metrics);
            int bright = ((int) briTotal/metrics);
            int ringmo = ((int) rmoTotal/metrics);

            Globals.METRIC_BRIGHT = bright;
            Globals.METRIC_VOLUME = volume;
            Globals.METRIC_RING   = ringmo;

        }
    }
}
