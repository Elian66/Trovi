package com.android.trovi.BackgroundCollect;

import android.content.Context;
import android.provider.Settings;

import com.android.trovi.Utils.Globals;

public class BrightnessCollect {

    public String checkBrightness(Context context){
        int bright = Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, 0);

        Globals.COL_BRIGHT = bright;

        return Integer.toString(bright);
    }
}
