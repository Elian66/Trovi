package com.android.trovi.ScreenData;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.provider.Settings;

import com.android.trovi.R;
import com.android.trovi.Screens.HomeActivity;

public class PhoneInfo {

    //Ringmode
    public String checkRingmode(Context context){
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int ringMode = am.getRingerMode();
        if (ringMode==0){
            return context.getResources().getString(R.string.ring_01);
            //ring_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_off_black_24dp));
        }
        else if (ringMode==1){
            return context.getResources().getString(R.string.ring_02);
            //ring_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_vibration_black_24dp));
        }
        else{
            return context.getResources().getString(R.string.ring_03);
           //ring_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_up_black_24dp));
        }
    }

    //Volume
    public String checkVolume(Context context){
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolumePercentage = 100 * currentVolume/maxVolume;

        return Integer.toString(currentVolumePercentage)+"%";
    }

    //Luminosity
    public String checkLuminosity(Context context){
        int bright = Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, 0);

        int currentBrightPercentage = 100 * bright/255;

        return Integer.toString(currentBrightPercentage)+"%";
    }

}
