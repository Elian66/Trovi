package com.android.trovi.BackgroundCollect;

import android.content.Context;
import android.media.AudioManager;

import com.android.trovi.Utils.Globals;

public class VolumeCollect {
    public String checkVolume(Context context){
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        Globals.COL_VOLUME = currentVolume;

        return Integer.toString(currentVolume);
    }
}
