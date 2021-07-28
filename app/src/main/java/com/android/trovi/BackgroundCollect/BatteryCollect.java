package com.android.trovi.BackgroundCollect;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;

public class BatteryCollect {
    //POWER SAVE
    public static boolean couldBePowerSaveMode(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (powerManager != null) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isPowerSaveModeAndroid(Context context) {
        boolean isPowerSaveMode = false;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (pm != null) isPowerSaveMode = pm.isPowerSaveMode();
        }
        return isPowerSaveMode;
    }

}
