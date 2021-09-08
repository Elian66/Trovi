package com.android.trovi.Screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.trovi.R;
import com.davidmiguel.multistateswitch.MultiStateSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PreferencesActivity extends AppCompatActivity {

    TextView pref, coletar, dados, importancia;
    TextView updtit, updatetxt;
    TextView pref_bright_title,pref_bright_autochange_title,pref_bright_autochange_subtitle;
    TextView pref_bright_saving_title,pref_bright_saving_subtitle;
    TextView pref_bright_confortable_title,pref_bright_confortable_subtitle;
    TextView pref_volume_title,pref_volume_louder;
    TextView pref_ringmode_title,pref_ringmode_meetings;
    TextView pref_meetings_title,pref_meetings_check;
    TextView pref_places_title,pref_places_ask_title,pref_places_ask_subtitle;
    RelativeLayout rlcol1,rlcol2,rlcol3;
    Typeface poppins_semibold,poppins_regular;
    LabeledSwitch swcol,swupd,pref_bright_autochange_title_sw;
    LabeledSwitch pref_bright_saving_title_sw,pref_bright_confortable_title_sw;
    LabeledSwitch pref_volume_louder_sw,pref_ringmode_meetings_sw;
    LabeledSwitch pref_meetings_check_sw,pref_places_ask_title_sw;
    LinearLayout llcol,llupd;

    boolean allowChanges,allowCollect,brightness_sensibility,save_battery_bright,comfortable_view;
    boolean louder_volume,meeting_ringmode,meeting_prior,save_places;
    boolean collected_time,collected_battery,collected_connection,collected_saving;
    boolean collected_localbright,collected_localvolume,collected_bluetooth,collected_headphone;
    String importance_time,importance_battery,importance_connection,importance_saving;
    String importance_localbright,importance_localvolume,importance_bluetooth,importance_headphone;

    boolean update_volume,update_bright,update_ringmode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        allowChanges = prefs.getBoolean("allowChanges", true);
        allowCollect = prefs.getBoolean("allowCollect", true);
        brightness_sensibility = prefs.getBoolean("brightness_sensibility",true);
        save_battery_bright = prefs.getBoolean("save_battery_bright",true);
        comfortable_view = prefs.getBoolean("comfortable_view",true);
        louder_volume = prefs.getBoolean("louder_volume",true);
        meeting_ringmode = prefs.getBoolean("meeting_ringmode",true);
        meeting_prior = prefs.getBoolean("meeting_prior",true);
        save_places = prefs.getBoolean("save_places",true);

        collected_time = prefs.getBoolean("collected_time",true);
        collected_battery = prefs.getBoolean("collected_battery",true);
        collected_connection = prefs.getBoolean("collected_connection",true);
        collected_saving = prefs.getBoolean("collected_saving",true);
        collected_localbright = prefs.getBoolean("collected_localbright",true);
        collected_localvolume = prefs.getBoolean("collected_localvolume",true);
        collected_bluetooth = prefs.getBoolean("collected_bluetooth",true);
        collected_headphone = prefs.getBoolean("collected_headphone",true);

        importance_time = prefs.getString("importance_time","");
        importance_battery = prefs.getString("importance_battery","");
        importance_connection = prefs.getString("importance_connection","");
        importance_saving = prefs.getString("importance_saving","");
        importance_localbright = prefs.getString("importance_localbright","");
        importance_localvolume = prefs.getString("importance_localvolume","");
        importance_bluetooth = prefs.getString("importance_bluetooth","");
        importance_headphone = prefs.getString("importance_headphone","");

        update_volume = prefs.getBoolean("update_volume",true);
        update_bright = prefs.getBoolean("update_bright",true);
        update_ringmode = prefs.getBoolean("update_ringmode",true);

        final SharedPreferences.Editor editor = prefs.edit();

        initAll();

        swcol.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    llcol.setVisibility(View.VISIBLE);
                    editor.putBoolean("allowCollect", true);
                    editor.apply();
                }else {
                    llcol.setVisibility(View.GONE);
                    editor.putBoolean("allowCollect", false);
                    editor.apply();
                }
            }
        });

        swupd.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    llupd.setVisibility(View.VISIBLE);
                    editor.putBoolean("allowChanges", true);
                    editor.apply();

                }else {
                    llupd.setVisibility(View.GONE);
                    editor.putBoolean("allowChanges", false);
                    editor.apply();
                }
            }
        });

        pref_bright_autochange_title_sw.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    editor.putBoolean("brightness_sensibility",true);
                    editor.apply();
                }else {
                    editor.putBoolean("brightness_sensibility",false);
                    editor.apply();
                }
            }
        });

        pref_bright_saving_title_sw.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    editor.putBoolean("save_battery_bright",true);
                    editor.apply();
                }else {
                    editor.putBoolean("save_battery_bright",false);
                    editor.apply();
                }
            }
        });

        pref_bright_confortable_title_sw.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    editor.putBoolean("comfortable_view",true);
                    editor.apply();
                }else {
                    editor.putBoolean("comfortable_view",false);
                    editor.apply();
                }
            }
        });

        pref_volume_louder_sw.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    editor.putBoolean("louder_volume",true);
                    editor.apply();
                }else {
                    editor.putBoolean("louder_volume",false);
                    editor.apply();
                }
            }
        });

        pref_ringmode_meetings_sw.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    editor.putBoolean("meeting_ringmode",true);
                    editor.apply();
                }else {
                    editor.putBoolean("meeting_ringmode",false);
                    editor.apply();
                }
            }
        });

        pref_meetings_check_sw.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    editor.putBoolean("meeting_prior",true);
                    editor.apply();
                }else {
                    editor.putBoolean("meeting_prior",false);
                    editor.apply();
                }
            }
        });

        pref_places_ask_title_sw.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean b) {
                if (b){
                    editor.putBoolean("save_places",true);
                    editor.apply();
                }else {
                    editor.putBoolean("save_places",false);
                    editor.apply();
                }
            }
        });

        rlcol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PreferencesActivity.this);
                alertDialog.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(PreferencesActivity.this);
                View layout_home = inflater.inflate(R.layout.layout_collect_data, null);

                final TextView pref_collect_title = layout_home.findViewById(R.id.pref_collect_title);
                final TextView pref_collect_subtitle_01 = layout_home.findViewById(R.id.pref_collect_subtitle_01);
                final TextView pref_collect_subtitle_02 = layout_home.findViewById(R.id.pref_collect_subtitle_02);

                final TextView pref_collect_battery = layout_home.findViewById(R.id.pref_collect_battery);
                final TextView pref_collect_time = layout_home.findViewById(R.id.pref_collect_time);
                final TextView pref_collect_connection = layout_home.findViewById(R.id.pref_collect_connection);
                final TextView pref_collect_saving = layout_home.findViewById(R.id.pref_collect_saving);
                final TextView pref_collect_localvolume = layout_home.findViewById(R.id.pref_collect_localvolume);
                final TextView pref_collect_localbright = layout_home.findViewById(R.id.pref_collect_localbright);
                final TextView pref_collect_bluetooth = layout_home.findViewById(R.id.pref_collect_bluetooth);
                final TextView pref_collect_headphone = layout_home.findViewById(R.id.pref_collect_headphone);

                final LabeledSwitch pref_collect_battery_sw = layout_home.findViewById(R.id.pref_collect_battery_sw);
                final LabeledSwitch pref_collect_time_sw = layout_home.findViewById(R.id.pref_collect_time_sw);
                final LabeledSwitch pref_collect_connection_sw = layout_home.findViewById(R.id.pref_collect_connection_sw);
                final LabeledSwitch pref_collect_saving_sw = layout_home.findViewById(R.id.pref_collect_saving_sw);
                final LabeledSwitch pref_collect_localvolume_sw = layout_home.findViewById(R.id.pref_collect_localvolume_sw);
                final LabeledSwitch pref_collect_localbright_sw = layout_home.findViewById(R.id.pref_collect_localbright_sw);
                final LabeledSwitch pref_collect_bluetooth_sw = layout_home.findViewById(R.id.pref_collect_bluetooth_sw);
                final LabeledSwitch pref_collect_headphone_sw = layout_home.findViewById(R.id.pref_collect_headphone_sw);

                if (collected_battery){
                    pref_collect_battery_sw.setOn(true);
                }else {
                    pref_collect_battery_sw.setOn(false);
                }
                if (collected_time){
                    pref_collect_time_sw.setOn(true);
                }else {
                    pref_collect_time_sw.setOn(false);
                }
                if (collected_connection){
                    pref_collect_connection_sw.setOn(true);
                }else {
                    pref_collect_connection_sw.setOn(false);
                }
                if (collected_saving){
                    pref_collect_saving_sw.setOn(true);
                }else {
                    pref_collect_saving_sw.setOn(false);
                }
                if (collected_localvolume){
                    pref_collect_localvolume_sw.setOn(true);
                }else {
                    pref_collect_localvolume_sw.setOn(false);
                }
                if (collected_localbright){
                    pref_collect_localbright_sw.setOn(true);
                }else {
                    pref_collect_localbright_sw.setOn(false);
                }
                if (collected_bluetooth){
                    pref_collect_bluetooth_sw.setOn(true);
                }else {
                    pref_collect_bluetooth_sw.setOn(false);
                }
                if (collected_headphone){
                    pref_collect_headphone_sw.setOn(true);
                }else {
                    pref_collect_headphone_sw.setOn(false);
                }

                final Button edit_btn = layout_home.findViewById(R.id.edit_btn);

                pref_collect_battery_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_battery", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_battery", false);
                            editor.apply();
                        }
                    }
                });
                pref_collect_time_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_time", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_time", false);
                            editor.apply();
                        }
                    }
                });
                pref_collect_connection_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_connection", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_connection", false);
                            editor.apply();
                        }
                    }
                });
                pref_collect_saving_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_saving", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_saving", false);
                            editor.apply();
                        }
                    }
                });
                pref_collect_localvolume_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_localvolume", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_localvolume", false);
                            editor.apply();
                        }
                    }
                });
                pref_collect_localbright_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_localbright", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_localbright", false);
                            editor.apply();
                        }
                    }
                });
                pref_collect_bluetooth_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_bluetooth", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_bluetooth", false);
                            editor.apply();
                        }
                    }
                });
                pref_collect_headphone_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("collected_headphone", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("collected_headphone", false);
                            editor.apply();
                        }
                    }
                });

                pref_collect_title.setTypeface(poppins_regular);
                pref_collect_subtitle_01.setTypeface(poppins_regular);
                pref_collect_subtitle_02.setTypeface(poppins_regular);
                pref_collect_battery.setTypeface(poppins_regular);
                pref_collect_time.setTypeface(poppins_regular);
                pref_collect_connection.setTypeface(poppins_regular);
                pref_collect_saving.setTypeface(poppins_regular);
                pref_collect_localvolume.setTypeface(poppins_regular);
                pref_collect_localbright.setTypeface(poppins_regular);
                pref_collect_bluetooth.setTypeface(poppins_regular);
                pref_collect_headphone.setTypeface(poppins_regular);
                edit_btn.setTypeface(poppins_regular);

                alertDialog.setView(layout_home);

                edit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alertDialog.show();
            }
        });

        rlcol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PreferencesActivity.this);
                alertDialog.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(PreferencesActivity.this);
                View layout_home = inflater.inflate(R.layout.layout_collect_importance, null);

                final TextView pref_collect_title = layout_home.findViewById(R.id.pref_importance_title);
                final TextView pref_collect_subtitle_01 = layout_home.findViewById(R.id.pref_importance_subtitle);
                final TextView pref_collect_battery = layout_home.findViewById(R.id.pref_importance_battery);
                final TextView pref_collect_time = layout_home.findViewById(R.id.pref_importance_time);
                final TextView pref_collect_connection = layout_home.findViewById(R.id.pref_importance_connection);
                final TextView pref_collect_saving = layout_home.findViewById(R.id.pref_importance_saving);
                final TextView pref_collect_localvolume = layout_home.findViewById(R.id.pref_importance_localvolume);
                final TextView pref_collect_localbright = layout_home.findViewById(R.id.pref_importance_localbright);
                final TextView pref_collect_bluetooth = layout_home.findViewById(R.id.pref_importance_bluetooth);
                final TextView pref_collect_headphone = layout_home.findViewById(R.id.pref_importance_headphone);
                final Button edit_btn = layout_home.findViewById(R.id.edit_btn);

                final MultiStateSwitch pref_collect_time_sw = layout_home.findViewById(R.id.pref_collect_time_sw);
                final MultiStateSwitch pref_collect_battery_sw = layout_home.findViewById(R.id.pref_collect_battery_sw);
                final MultiStateSwitch pref_collect_connection_sw = layout_home.findViewById(R.id.pref_collect_connection_sw);
                final MultiStateSwitch pref_collect_saving_sw = layout_home.findViewById(R.id.pref_collect_saving_sw);
                final MultiStateSwitch pref_collect_localbright_sw = layout_home.findViewById(R.id.pref_collect_localbright_sw);
                final MultiStateSwitch pref_collect_localvolume_sw = layout_home.findViewById(R.id.pref_collect_localvolume_sw);
                final MultiStateSwitch pref_collect_bluetooth_sw = layout_home.findViewById(R.id.pref_collect_bluetooth_sw);
                final MultiStateSwitch pref_collect_headphone_sw = layout_home.findViewById(R.id.pref_collect_headphone_sw);

                pref_collect_title.setTypeface(poppins_regular);
                pref_collect_subtitle_01.setTypeface(poppins_regular);
                pref_collect_battery.setTypeface(poppins_regular);
                pref_collect_time.setTypeface(poppins_regular);
                pref_collect_connection.setTypeface(poppins_regular);
                pref_collect_saving.setTypeface(poppins_regular);
                pref_collect_localvolume.setTypeface(poppins_regular);
                pref_collect_localbright.setTypeface(poppins_regular);
                pref_collect_bluetooth.setTypeface(poppins_regular);
                pref_collect_headphone.setTypeface(poppins_regular);
                edit_btn.setTypeface(poppins_regular);

                List myList = new ArrayList();
                myList.add("1");
                myList.add("2");
                myList.add("3");

                pref_collect_battery_sw.addStatesFromStrings(myList);
                pref_collect_time_sw.addStatesFromStrings(myList);
                pref_collect_connection_sw.addStatesFromStrings(myList);
                pref_collect_localbright_sw.addStatesFromStrings(myList);
                pref_collect_localvolume_sw.addStatesFromStrings(myList);
                pref_collect_saving_sw.addStatesFromStrings(myList);
                pref_collect_bluetooth_sw.addStatesFromStrings(myList);
                pref_collect_headphone_sw.addStatesFromStrings(myList);

                alertDialog.setView(layout_home);

                edit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alertDialog.show();
            }
        });

        rlcol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PreferencesActivity.this);
                alertDialog.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(PreferencesActivity.this);
                View layout_home = inflater.inflate(R.layout.layout_update_data, null);

                final TextView pref_update_title = layout_home.findViewById(R.id.pref_update_title);
                final TextView pref_update_subtitle = layout_home.findViewById(R.id.pref_update_subtitle);
                final TextView pref_update_ringmode = layout_home.findViewById(R.id.pref_update_ringmode);
                final TextView pref_update_volume = layout_home.findViewById(R.id.pref_update_volume);
                final TextView pref_update_bright = layout_home.findViewById(R.id.pref_update_bright);
                final Button edit_btn = layout_home.findViewById(R.id.edit_btn);

                final LabeledSwitch pref_update_ringmode_sw = layout_home.findViewById(R.id.pref_update_ringmode_sw);
                final LabeledSwitch pref_update_volume_sw = layout_home.findViewById(R.id.pref_update_volume_sw);
                final LabeledSwitch pref_update_bright_sw = layout_home.findViewById(R.id.pref_update_bright_sw);

                pref_update_title.setTypeface(poppins_regular);
                pref_update_subtitle.setTypeface(poppins_regular);
                pref_update_ringmode.setTypeface(poppins_regular);
                pref_update_volume.setTypeface(poppins_regular);
                pref_update_bright.setTypeface(poppins_regular);
                edit_btn.setTypeface(poppins_regular);

                if (update_volume){
                    pref_update_volume_sw.setOn(true);
                }else {
                    pref_update_volume_sw.setOn(false);
                }
                if (update_bright){
                    pref_update_bright_sw.setOn(true);
                }else {
                    pref_update_bright_sw.setOn(false);
                }
                if (update_ringmode){
                    pref_update_ringmode_sw.setOn(true);
                }else {
                    pref_update_ringmode_sw.setOn(false);
                }

                pref_update_volume_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("update_volume", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("update_volume", false);
                            editor.apply();
                        }
                    }
                });
                pref_update_bright_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("update_bright", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("update_bright", false);
                            editor.apply();
                        }
                    }
                });
                pref_update_ringmode_sw.setOnToggledListener(new OnToggledListener() {
                    @Override
                    public void onSwitched(ToggleableView toggleableView, boolean b) {
                        if (b){
                            editor.putBoolean("update_ringmode", true);
                            editor.apply();
                        }else {
                            editor.putBoolean("update_ringmode", false);
                            editor.apply();
                        }
                    }
                });

                alertDialog.setView(layout_home);

                edit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alertDialog.show();
            }
        });
    }

    private void initAll(){
        poppins_semibold = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-SemiBold.ttf");
        poppins_regular = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-Regular.ttf");

        //Collect
        pref = findViewById(R.id.preferences);
        coletar = findViewById(R.id.pref_title);
        dados = findViewById(R.id.pref_collect_01_txt);
        importancia = findViewById(R.id.pref_collect_02_txt);
        rlcol1 = findViewById(R.id.pref_collect_01);
        rlcol2 = findViewById(R.id.pref_collect_02);
        swcol = findViewById(R.id.pref_collect_sw);
        llcol = findViewById(R.id.pref_collect_ll);

        pref.setTypeface(poppins_semibold);
        coletar.setTypeface(poppins_regular);
        dados.setTypeface(poppins_regular);
        importancia.setTypeface(poppins_regular);

        if (allowCollect){
            swcol.setOn(true);
            llcol.setVisibility(View.VISIBLE);
        } else{
            swcol.setOn(false);
            llcol.setVisibility(View.GONE);
        }

        //Updates
        updtit = findViewById(R.id.pref_changes_title);
        updatetxt = findViewById(R.id.pref_update_title_txt);
        rlcol3 = findViewById(R.id.pref_update_title);
        swupd = findViewById(R.id.pref_changes_sw);
        llupd = findViewById(R.id.pref_updates_ll);

        updtit.setTypeface(poppins_regular);
        updatetxt.setTypeface(poppins_regular);
        llupd.setVisibility(View.GONE);

        if (allowChanges){
            swupd.setOn(true);
            llupd.setVisibility(View.VISIBLE);
        } else{
            swupd.setOn(false);
            llupd.setVisibility(View.GONE);
        }

        //Brightness
        pref_bright_title = findViewById(R.id.pref_bright_title);
        pref_bright_autochange_title = findViewById(R.id.pref_bright_autochange_title);
        pref_bright_autochange_subtitle = findViewById(R.id.pref_bright_autochange_subtitle);
        pref_bright_saving_title = findViewById(R.id.pref_bright_saving_title);
        pref_bright_saving_subtitle = findViewById(R.id.pref_bright_saving_subtitle);
        pref_bright_confortable_title = findViewById(R.id.pref_bright_confortable_title);
        pref_bright_confortable_subtitle = findViewById(R.id.pref_bright_confortable_subtitle);
        pref_bright_autochange_title_sw = findViewById(R.id.pref_bright_autochange_title_sw);
        pref_bright_saving_title_sw = findViewById(R.id.pref_bright_saving_title_sw);
        pref_bright_confortable_title_sw = findViewById(R.id.pref_bright_confortable_title_sw);

        pref_bright_title.setTypeface(poppins_regular);
        pref_bright_autochange_title.setTypeface(poppins_regular);
        pref_bright_autochange_subtitle.setTypeface(poppins_regular);
        pref_bright_saving_title.setTypeface(poppins_regular);
        pref_bright_saving_subtitle.setTypeface(poppins_regular);
        pref_bright_confortable_title.setTypeface(poppins_regular);
        pref_bright_confortable_subtitle.setTypeface(poppins_regular);
        if (brightness_sensibility){
            pref_bright_autochange_title_sw.setOn(true);
        }else {
            pref_bright_autochange_title_sw.setOn(false);
        }

        if (save_battery_bright){
            pref_bright_saving_title_sw.setOn(true);
        }else{
            pref_bright_saving_title_sw.setOn(false);
        }

        if (comfortable_view){
            pref_bright_confortable_title_sw.setOn(true);
        }else{
            pref_bright_confortable_title_sw.setOn(false);
        }

        //Volume
        pref_volume_title = findViewById(R.id.pref_volume_title);
        pref_volume_louder = findViewById(R.id.pref_volume_louder);
        pref_volume_louder_sw = findViewById(R.id.pref_volume_louder_sw);

        pref_volume_title.setTypeface(poppins_regular);
        pref_volume_louder.setTypeface(poppins_regular);

        if (louder_volume){
            pref_volume_louder_sw.setOn(true);
        }else {
            pref_volume_louder_sw.setOn(false);
        }

        //Ringmode
        pref_ringmode_title = findViewById(R.id.pref_ringmode_title);
        pref_ringmode_meetings = findViewById(R.id.pref_ringmode_meetings);
        pref_ringmode_meetings_sw = findViewById(R.id.pref_ringmode_meetings_sw);

        pref_ringmode_title.setTypeface(poppins_regular);
        pref_ringmode_meetings.setTypeface(poppins_regular);

        if (meeting_ringmode){
            pref_ringmode_meetings_sw.setOn(true);
        }else {
            pref_ringmode_meetings_sw.setOn(false);
        }

        //Meetings
        pref_meetings_title = findViewById(R.id.pref_meetings_title);
        pref_meetings_check = findViewById(R.id.pref_meetings_check);
        pref_meetings_check_sw = findViewById(R.id.pref_meetings_check_sw);

        pref_meetings_title.setTypeface(poppins_regular);
        pref_meetings_check.setTypeface(poppins_regular);

        if (meeting_prior){
            pref_meetings_check_sw.setOn(true);
        }else{
            pref_meetings_check_sw.setOn(false);
        }

        //Found places
        pref_places_title = findViewById(R.id.pref_places_title);
        pref_places_ask_title = findViewById(R.id.pref_places_ask_title);
        pref_places_ask_subtitle = findViewById(R.id.pref_places_ask_subtitle);
        pref_places_ask_title_sw = findViewById(R.id.pref_places_ask_title_sw);

        pref_places_title.setTypeface(poppins_regular);
        pref_places_ask_title.setTypeface(poppins_regular);
        pref_places_ask_subtitle.setTypeface(poppins_regular);

        if (save_places){
            pref_places_ask_title_sw.setOn(true);
        }else{
            pref_places_ask_title_sw.setOn(false);
        }
    }
}