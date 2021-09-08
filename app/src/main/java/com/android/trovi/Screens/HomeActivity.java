package com.android.trovi.Screens;

import static com.android.trovi.Utils.Globals.meetNames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.trovi.AutoUpdates.CheckMetrics;
import com.android.trovi.BackgroundCollect.MeetingsCollect;
import com.android.trovi.Models.MeetingModel;
import com.android.trovi.R;
import com.android.trovi.ScreenData.PhoneInfo;
import com.android.trovi.ScreenData.UserAndDateInfo;
import com.android.trovi.Services.BackgroundService;
import com.android.trovi.Utils.Globals;
import com.android.trovi.ViewHolders.MyListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    TextView home_01,home_02,home_03,home_04;
    TextView home_05,home_06,home_07,home_08,home_09;
    TextView status_01,status_02,status_03,status_04;
    TextView home_collect_text,home_collect_status,home_collect_button;
    TextView home_changes_text,home_changes_status,home_changes_button;
    ImageView home_ring,home_volume,home_bright,home_battery,home_settings,home_nomeet;
    int level;

    List<String> meetInfo = new ArrayList<String>();
    List<String> meetDate = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);

        Paper.init(this);

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

        final Intent intent = new Intent(this, BackgroundService.class);
        intent.putExtra("type", Sensor.TYPE_LIGHT);

        startService(new Intent(getApplicationContext(), BackgroundService.class));

        home_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        home_collect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                final boolean allowCollect = prefs.getBoolean("allowCollect", true);
                final SharedPreferences.Editor editor = prefs.edit();

                if (allowCollect){
                    home_collect_status.setText(R.string.home_collect_status_01);
                    home_collect_button.setText(R.string.home_collect_button_01);
                    editor.putBoolean("allowCollect", false);
                    editor.apply();
                }else {
                    home_collect_status.setText(R.string.home_collect_status_02);
                    home_collect_button.setText(R.string.home_collect_button_02);
                    editor.putBoolean("allowCollect", true);
                    editor.apply();
                }

            }
        });

        home_changes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                final boolean allowChanges = prefs.getBoolean("allowChanges", true);
                final SharedPreferences.Editor editor = prefs.edit();

                if (allowChanges){
                    home_changes_status.setText(R.string.home_changes_status_01);
                    home_changes_button.setText(R.string.home_changes_button_01);
                    editor.putBoolean("allowChanges", false);
                    editor.apply();
                }else {
                    home_changes_status.setText(R.string.home_changes_status_02);
                    home_changes_button.setText(R.string.home_changes_button_02);
                    editor.putBoolean("allowChanges", true);
                    editor.apply();
                }

            }
        });

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
        home_09 = findViewById(R.id.home_09);
        status_01 = findViewById(R.id.status_01);
        status_02 = findViewById(R.id.status_02);
        status_03 = findViewById(R.id.status_03);
        status_04 = findViewById(R.id.status_04);
        home_collect_text = findViewById(R.id.home_collect_text);
        home_collect_status = findViewById(R.id.home_collect_status);
        home_collect_button = findViewById(R.id.home_collect_button);
        home_changes_text = findViewById(R.id.home_changes_text);
        home_changes_status = findViewById(R.id.home_changes_status);
        home_changes_button = findViewById(R.id.home_changes_button);
        home_settings = findViewById(R.id.home_settings);
        home_nomeet = findViewById(R.id.home_nomeet);

        home_01.setTypeface(poppins_semibold);
        home_02.setTypeface(poppins_semibold);
        home_03.setTypeface(poppins_semibold);
        home_04.setTypeface(poppins_semibold);
        home_05.setTypeface(poppins_regular);
        home_06.setTypeface(poppins_regular);
        home_07.setTypeface(poppins_regular);
        home_08.setTypeface(poppins_regular);
        home_09.setTypeface(poppins_regular);
        status_01.setTypeface(poppins_regular);
        status_02.setTypeface(poppins_regular);
        status_03.setTypeface(poppins_regular);
        status_04.setTypeface(poppins_regular);
        home_collect_text.setTypeface(poppins_regular);
        home_collect_status.setTypeface(poppins_regular);
        home_collect_button.setTypeface(poppins_semibold);
        home_changes_text.setTypeface(poppins_regular);
        home_changes_status.setTypeface(poppins_regular);
        home_changes_button.setTypeface(poppins_semibold);

        //Change images
        home_ring = findViewById(R.id.home_ring);
        home_volume = findViewById(R.id.home_volume);
        home_bright = findViewById(R.id.home_bright);
        home_battery = findViewById(R.id.home_battery);

        PhoneInfo p1 = new PhoneInfo();
        if (p1.checkRingmode(this).equals("0")){
            home_ring.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_off_black_24dp));
        }
        else if (p1.checkRingmode(this).equals("1")){
            home_ring.setImageDrawable(getResources().getDrawable(R.drawable.ic_vibration_black_24dp));
        }
        else {
            home_ring.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_up_black_24dp));
        }

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (am.getStreamVolume(AudioManager.STREAM_MUSIC)==0){
            home_volume.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_mute_black_24dp));
        }
        else if (am.getStreamVolume(AudioManager.STREAM_MUSIC)<6){
            home_volume.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_down_black_24dp));
        }
        else{
            home_volume.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_up_black_24dp));
        }

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        final boolean firstCollect = prefs.getBoolean("firstCollect", true);
        final boolean firstChanges = prefs.getBoolean("firstChanges", true);
        final boolean allowCollect = prefs.getBoolean("allowCollect", true);
        final boolean allowChanges = prefs.getBoolean("allowChanges", true);
        final SharedPreferences.Editor editor = prefs.edit();

        if (!allowCollect){
            home_collect_status.setText(R.string.home_collect_status_01);
            home_collect_button.setText(R.string.home_collect_button_01);
        }else{
            home_collect_status.setText(R.string.home_collect_status_02);
            home_collect_button.setText(R.string.home_collect_button_02);
        }

        if (!allowChanges){
            home_changes_status.setText(R.string.home_changes_status_01);
            home_changes_button.setText(R.string.home_changes_button_01);
        }else{
            home_changes_status.setText(R.string.home_changes_status_02);
            home_changes_button.setText(R.string.home_changes_button_02);
        }

        if (firstCollect){
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
            alertDialog.setCancelable(false);

            LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
            final View layout_allow_collect = inflater.inflate(R.layout.layout_allow_collect, null);

            final TextView allowcollect_title,allowcollect_message;
            final Button allowcollect_button;

            allowcollect_title = layout_allow_collect.findViewById(R.id.allowcollect_title);
            allowcollect_message = layout_allow_collect.findViewById(R.id.allowcollect_message);
            allowcollect_button = layout_allow_collect.findViewById(R.id.allowcollect_button);

            allowcollect_title.setTypeface(poppins_semibold);
            allowcollect_message.setTypeface(poppins_regular);
            allowcollect_button.setTypeface(poppins_regular);

            allowcollect_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editor.putBoolean("allowCollect", true);
                    editor.apply();

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            editor.putBoolean("firstCollect", false);
            editor.apply();

            alertDialog.setView(layout_allow_collect);

        }

        if (firstChanges){
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
            alertDialog.setCancelable(false);
            LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
            final View layout_allow_collect = inflater.inflate(R.layout.layout_allow_changes, null);

            final TextView allowchanges_title,allowchanges_message;
            final Button allowchanges_button;

            allowchanges_title = layout_allow_collect.findViewById(R.id.allowchanges_title);
            allowchanges_message = layout_allow_collect.findViewById(R.id.allowchanges_message);
            allowchanges_button = layout_allow_collect.findViewById(R.id.allowchanges_button);

            allowchanges_title.setTypeface(poppins_semibold);
            allowchanges_message.setTypeface(poppins_regular);
            allowchanges_button.setTypeface(poppins_regular);

            allowchanges_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editor.putBoolean("allowChanges", true);
                    editor.apply();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            editor.putBoolean("firstChanges", false);
            editor.apply();

            alertDialog.setView(layout_allow_collect);

        }

    }

    private void configs(){
        String[] separated = Globals.CURRENT_USER.getName().split(" ");
        home_01.setText("Olá, "+separated[0]);

        UserAndDateInfo u1 = new UserAndDateInfo();
        home_03.setText(u1.checkHour());
        home_04.setText(u1.checkDate());

        PhoneInfo p1 = new PhoneInfo();
        status_01.setText(p1.checkRingmode(this));
        status_02.setText(p1.checkVolume(this));
        status_03.setText(p1.checkLuminosity(this));
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        //MEET
        String interests = "";
        for(int i=0;i<MeetingsCollect.readCalendarEvent(getBaseContext()).size();i++){

            for (Iterator<String> it = MeetingsCollect.readCalendarEvent(getBaseContext()).iterator(); it.hasNext(); ) {
                String str = it.next();

                String[] separated2 = str.split(":@:");
                final String reuniaoNome = separated2[0];
                final String reuniaoData = separated2[1];

                String[] rDataSplit = reuniaoData.split(" ");
                final String data01 = rDataSplit[0];
                final String data02 = rDataSplit[1];
                final String data03 = rDataSplit[2];

                String[] horaSplit = data02.split(":");
                String hora = "";
                if (data03.equals("AM")){
                    hora = horaSplit[0];
                }else{
                    hora = Integer.toString(12+Integer.parseInt(horaSplit[0]));
                }
                String minu = horaSplit[1];
                String segu = horaSplit[2];

                Date date = new Date();
                String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
                String day2         = (String) DateFormat.format("dd",   date); // 20
                String monthString  = (String) DateFormat.format("MMM",  date); // Jun
                String monthNumber  = (String) DateFormat.format("MM",   date); // 06
                String year         = (String) DateFormat.format("yyyy", date); // 2013

                //Data Atual
                String meuDia = day2 + "/" + monthNumber + "/" + year;

                MeetingModel[] meetIt = new MeetingModel[]{};

                if (data01.equals(meuDia)){
                    interests+="\n"+reuniaoNome+"\n"+reuniaoData+"\n----";
                    //Globals.meetNames interests;
                    if (!meetInfo.contains(reuniaoNome)){
                        meetInfo.add(reuniaoNome);
                        meetDate.add(reuniaoData);
                    }
                }
            }
        }

        List<MeetingModel> meModels = new ArrayList<>();

        meModels.clear();
        for (int i = 0; i<meetInfo.size();i++){

            if (meetInfo.size()==1){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0))
                };
            }
            else if (meetInfo.size()==2){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1))
                };
            }
            else if (meetInfo.size()==3){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2))
                };
            }
            else if (meetInfo.size()==4){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2)),
                        new MeetingModel(meetInfo.get(3),meetDate.get(3))
                };
            }
            else if (meetInfo.size()==5){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2)),
                        new MeetingModel(meetInfo.get(3),meetDate.get(3)),
                        new MeetingModel(meetInfo.get(4),meetDate.get(4))
                };
            }
            else if (meetInfo.size()==6){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2)),
                        new MeetingModel(meetInfo.get(3),meetDate.get(3)),
                        new MeetingModel(meetInfo.get(4),meetDate.get(4)),
                        new MeetingModel(meetInfo.get(5),meetDate.get(5))
                };
            }
            else if (meetInfo.size()==7){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2)),
                        new MeetingModel(meetInfo.get(3),meetDate.get(3)),
                        new MeetingModel(meetInfo.get(4),meetDate.get(4)),
                        new MeetingModel(meetInfo.get(5),meetDate.get(5)),
                        new MeetingModel(meetInfo.get(6),meetDate.get(6))
                };
            }
            else if (meetInfo.size()==8){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2)),
                        new MeetingModel(meetInfo.get(3),meetDate.get(3)),
                        new MeetingModel(meetInfo.get(4),meetDate.get(4)),
                        new MeetingModel(meetInfo.get(5),meetDate.get(5)),
                        new MeetingModel(meetInfo.get(6),meetDate.get(6)),
                        new MeetingModel(meetInfo.get(7),meetDate.get(7))
                };
            }
            else if (meetInfo.size()==9){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2)),
                        new MeetingModel(meetInfo.get(3),meetDate.get(3)),
                        new MeetingModel(meetInfo.get(4),meetDate.get(4)),
                        new MeetingModel(meetInfo.get(5),meetDate.get(5)),
                        new MeetingModel(meetInfo.get(6),meetDate.get(6)),
                        new MeetingModel(meetInfo.get(7),meetDate.get(7)),
                        new MeetingModel(meetInfo.get(8),meetDate.get(8))
                };
            }
            else if (meetInfo.size()==10){
                meetNames = new MeetingModel[]{
                        new MeetingModel(meetInfo.get(0),meetDate.get(0)),
                        new MeetingModel(meetInfo.get(1),meetDate.get(1)),
                        new MeetingModel(meetInfo.get(2),meetDate.get(2)),
                        new MeetingModel(meetInfo.get(3),meetDate.get(3)),
                        new MeetingModel(meetInfo.get(4),meetDate.get(4)),
                        new MeetingModel(meetInfo.get(5),meetDate.get(5)),
                        new MeetingModel(meetInfo.get(6),meetDate.get(6)),
                        new MeetingModel(meetInfo.get(7),meetDate.get(7)),
                        new MeetingModel(meetInfo.get(8),meetDate.get(8)),
                        new MeetingModel(meetInfo.get(9),meetDate.get(9))
                };
            }

        }

        if (meetNames!=null){
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_meetings);
            MyListAdapter adapter = new MyListAdapter(meetNames);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        if (interests.isEmpty()){
            home_nomeet.setVisibility(View.VISIBLE);
        }else {
            home_nomeet.setVisibility(View.GONE);
        }

    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            status_04.setText(Integer.toString(level)+"%");

            if (level>90){
                home_battery.setImageDrawable(getResources().getDrawable(R.drawable.ic_battery_full_black_24dp));
            }
            else if (level>80){
                home_battery.setImageDrawable(getResources().getDrawable(R.drawable.ic_battery_90_black_24dp));
            }
            else if (level>70){
                home_battery.setImageDrawable(getResources().getDrawable(R.drawable.ic_battery_80_black_24dp));
            }
            else if (level>50){
                home_battery.setImageDrawable(getResources().getDrawable(R.drawable.ic_battery_60_black_24dp));
            }
            else if (level>40){
                home_battery.setImageDrawable(getResources().getDrawable(R.drawable.ic_battery_50_black_24dp));
            }
            else if (level>20){
                home_battery.setImageDrawable(getResources().getDrawable(R.drawable.ic_battery_30_black_24dp));
            }
            else{
                home_battery.setImageDrawable(getResources().getDrawable(R.drawable.ic_battery_20_black_24dp));
            }
        }
    };
}
