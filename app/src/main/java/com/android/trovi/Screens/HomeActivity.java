package com.android.trovi.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.android.trovi.R;
import com.android.trovi.ScreenData.PhoneInfo;
import com.android.trovi.ScreenData.UserAndDateInfo;
import com.android.trovi.Services.BackgroundService;

public class HomeActivity extends AppCompatActivity {

    TextView home_01,home_02,home_03,home_04;
    TextView home_05,home_06,home_07,home_08;
    TextView status_01,status_02,status_03,status_04;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);

        if (!Settings.canDrawOverlays(this)) {
            //Request permission if not authorized
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 0);
        }

        initAll();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                configs();
                            }
                        });
                    }
                } catch (InterruptedException e) {

                }
            }
        };

        t.start();

        Intent intent = new Intent(this, BackgroundService.class);
        intent.putExtra("type", Sensor.TYPE_LIGHT);

        startService(new Intent(getApplicationContext(), BackgroundService.class));

    }

    private void initAll(){
        Typeface poppins_semibold = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-SemiBold.ttf");
        Typeface poppins_regular = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-Regular.ttf");

        home_01 = findViewById(R.id.home_01);
        home_02 = findViewById(R.id.home_02);
        home_03 = findViewById(R.id.home_03);
        home_04 = findViewById(R.id.home_04);
        home_05 = findViewById(R.id.home_05);
        home_06 = findViewById(R.id.home_06);
        home_07 = findViewById(R.id.home_07);
        home_08 = findViewById(R.id.home_08);
        status_01 = findViewById(R.id.status_01);
        status_02 = findViewById(R.id.status_02);
        status_03 = findViewById(R.id.status_03);
        status_04 = findViewById(R.id.status_04);

        home_01.setTypeface(poppins_semibold);
        home_02.setTypeface(poppins_semibold);
        home_03.setTypeface(poppins_semibold);
        home_04.setTypeface(poppins_semibold);
        home_05.setTypeface(poppins_regular);
        home_06.setTypeface(poppins_regular);
        home_07.setTypeface(poppins_regular);
        home_08.setTypeface(poppins_regular);
        status_01.setTypeface(poppins_regular);
        status_02.setTypeface(poppins_regular);
        status_03.setTypeface(poppins_regular);
        status_04.setTypeface(poppins_regular);

    }

    private void configs(){
        UserAndDateInfo u1 = new UserAndDateInfo();
        home_03.setText(u1.checkHour());
        home_04.setText(u1.checkDate());

        PhoneInfo p1 = new PhoneInfo();
        status_01.setText(p1.checkRingmode(this));
        status_02.setText(p1.checkVolume(this));
        status_03.setText(p1.checkLuminosity(this));
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            status_04.setText(Integer.toString(level)+"%");
        }
    };
}
