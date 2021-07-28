package com.android.trovi.BackgroundCollect;

import android.content.Context;
import android.media.AudioManager;

import com.android.trovi.R;
import com.android.trovi.Utils.Globals;

public class RingmodeCollect {
    public String checkRingmode(Context context){
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int ringMode = am.getRingerMode();

        Globals.COL_RING = ringMode;

        return Integer.toString(ringMode);
    }
}
