package com.android.trovi.AutoUpdates;

import android.util.Log;

import com.android.trovi.Services.BackgroundService;
import com.android.trovi.Utils.Globals;

public class UpdatePhone {
    public void makeChanges(){
        int metrics = 0;
        int volTotal = 0;
        int briTotal = 0;
        int rmoTotal = 0;

        if (CheckMetrics.ADB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.ADB_VOLUME);
            briTotal += Integer.parseInt(Globals.ADB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.ADB_RING);
        }

        if (CheckMetrics.BDB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.BDB_VOLUME);
            briTotal += Integer.parseInt(Globals.BDB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.BDB_RING);
        }

        if (CheckMetrics.CDB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.CDB_VOLUME);
            briTotal += Integer.parseInt(Globals.CDB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.CDB_RING);
        }

        if (CheckMetrics.DDB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.DDB_VOLUME);
            briTotal += Integer.parseInt(Globals.DDB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.DDB_RING);
        }

        if (CheckMetrics.EDB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.EDB_VOLUME);
            briTotal += Integer.parseInt(Globals.EDB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.EDB_RING);
        }

        if (CheckMetrics.FDB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.FDB_VOLUME);
            briTotal += Integer.parseInt(Globals.FDB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.FDB_RING);
        }

        if (CheckMetrics.GDB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.GDB_VOLUME);
            briTotal += Integer.parseInt(Globals.GDB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.GDB_RING);
        }

        if (CheckMetrics.HDB_STATUS){
            metrics++;
            volTotal += Integer.parseInt(Globals.GDB_VOLUME);
            briTotal += Integer.parseInt(Globals.GDB_BRIGHT);
            rmoTotal += Integer.parseInt(Globals.GDB_RING);
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
