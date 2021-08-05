package com.android.trovi.BackgroundCollect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.widget.Toast;

public class HeadphoneCollect {
    public String checkHeadphone(Context context){
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.isWiredHeadsetOn()){
            return "1";
        }else {
            return "0";
        }
    }
}
