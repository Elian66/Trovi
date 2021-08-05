package com.android.trovi.Services;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.android.trovi.AutoUpdates.CheckMetrics;
import com.android.trovi.AutoUpdates.DummyBrightnessActivity;
import com.android.trovi.AutoUpdates.MeetingsUpdates;
import com.android.trovi.BackgroundCollect.BatteryCollect;
import com.android.trovi.BackgroundCollect.BluetoothCollect;
import com.android.trovi.BackgroundCollect.BrightnessCollect;
import com.android.trovi.BackgroundCollect.ConnectionCollect;
import com.android.trovi.BackgroundCollect.DateTimeCollect;
import com.android.trovi.BackgroundCollect.HeadphoneCollect;
import com.android.trovi.BackgroundCollect.LocalDataCollect;
import com.android.trovi.BackgroundCollect.LocationCollect;
import com.android.trovi.BackgroundCollect.MeetingsCollect;
import com.android.trovi.BackgroundCollect.RingmodeCollect;
import com.android.trovi.BackgroundCollect.VolumeCollect;
import com.android.trovi.DataStorage.ADB;
import com.android.trovi.DataStorage.BDB;
import com.android.trovi.DataStorage.CDB;
import com.android.trovi.DataStorage.DDB;
import com.android.trovi.DataStorage.EDB;
import com.android.trovi.DataStorage.FDB;
import com.android.trovi.DataStorage.GDB;
import com.android.trovi.DataStorage.HDB;
import com.android.trovi.DataStorage.MeetingsStorage;
import com.android.trovi.DataStorage.PlacesStorage;
import com.android.trovi.Models.MeetingModel;
import com.android.trovi.R;
import com.android.trovi.Screens.HomeActivity;
import com.android.trovi.Screens.SignInActivity;
import com.android.trovi.Utils.Globals;
import com.google.android.gms.common.internal.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class BackgroundService extends Service {

    private static final String TAG = "MyLocationService";
    String hour,mins,secs,localvol;
    String latitude,longitude;
    String bright,volume;
    int level,plugged;
    String powerSave;
    String ringMode;

    public static MeetingModel[] meetNames;

    boolean fileFound = false;
    boolean readOnce = false;
    boolean chngOnce = false;
    boolean meetPrior = false;

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private float maxValue;

    public double totalBrilho = 0;
    public int totalTestesBri = 0;

    public double totalVolume = 0;
    public int totalTestesVol = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            startMyOwnForeground();
        }
        else{
            startForeground(1, new Notification());
        }

        final Handler handler =new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 10000);
                configurar();
            }
        }; handler.postDelayed(r, 0000);

        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
        return null;
    }

    //  oreo api approach
    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {

        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("Aplicação funcionando em segundo plano")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.drawable.ic_main_trovi)
                .build();
        startForeground(2, notification);
    }

    private void configurar(){
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        final boolean allowCollect = prefs.getBoolean("allowCollect", true);

        if (allowCollect){
            //BATTERY DATA
            this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

            //TODO: CREATE VARS
            //DATE TIME DATA
            DateTimeCollect dtc = new DateTimeCollect();
            hour = dtc.getHour();
            mins = dtc.getMins();
            secs = dtc.getSecs();

            //LOCAL DATA INIT
            //VOLUME
            //VOLUME LOCAL
            if ((Integer.parseInt(mins)%3)==2){
                if (Integer.parseInt(secs)>50){
                    int sampleRate = 8000;
                    AudioRecord audio;
                    try {
                        int bufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO,
                                AudioFormat.ENCODING_PCM_16BIT);
                        audio = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate,
                                AudioFormat.CHANNEL_IN_MONO,
                                AudioFormat.ENCODING_PCM_16BIT, bufferSize);

                        audio.startRecording();

                        short[] buffer = new short[bufferSize];

                        int bufferReadResult = 1;

                        if (audio != null) {

                            // Sense the voice...
                            bufferReadResult = audio.read(buffer, 0, bufferSize);
                            double sumLevel = 0;
                            for (int i = 0; i < bufferReadResult; i++) {
                                sumLevel += buffer[i];
                            }

                            if (Math.abs((sumLevel / bufferReadResult))>0){
                                totalTestesVol++;
                                totalVolume += Math.abs((sumLevel / bufferReadResult));
                            }

                            //localvol = Double.toString(Math.abs((sumLevel / bufferReadResult)));
                            //Log.d("RAWDATA","Amp:"+localvol);
                        }

                    } catch (Exception e) {
                        android.util.Log.e("TrackingFlow", "Exception", e);
                    }
                }
            }

            //BRIGHT
            sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


            //MAKE CHANGES

            //First check if it's meeting time
            //Save Meetings
            String interests = "";

            MeetingsCollect mc = new MeetingsCollect();
            for(String str: mc.readCalendarEvent(getBaseContext())){
                String[] separated = str.split(":@:");
                final String reuniaoNome = separated[0];
                final String reuniaoData = separated[1];

                String[] rDataSplit = reuniaoData.split(" ");
                final String data01 = rDataSplit[0];
                final String data02 = rDataSplit[1];
                final String data03 = rDataSplit[2];

                String[] horaSplit = data02.split(":");
                String hora;
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

                if (data01.equals(meuDia)){
                    interests+="\n"+reuniaoNome+"\n"+reuniaoData+"\n----";

                    Log.d("RAWDATA",reuniaoNome);

                    meetNames = new MeetingModel[]{
                            new MeetingModel("Name",reuniaoNome),
                            new MeetingModel("Time",reuniaoData),
                    };

                    //Silencia celular no horário da reunião
                    if (dtc.hours==Integer.parseInt(hora)&&dtc.mins>=Integer.parseInt(minu)){
                        //TODO: reunião como prioridade maior que outras alterações
                        meetPrior = true;
                        MeetingsStorage meetingsStorage = new MeetingsStorage();
                        meetingsStorage.createFile();
                    }else if (dtc.hours==Integer.parseInt(hora)+1&&dtc.mins<Integer.parseInt(minu)){
                        meetPrior = true;
                        MeetingsStorage meetingsStorage = new MeetingsStorage();
                        meetingsStorage.createFile();
                    }else {
                        meetPrior = false;
                    }
                }
            }

            if ((Integer.parseInt(mins)%15)==1){
                chngOnce = false;
            }


            //Check if user allowed changes
            final boolean allowChanges = prefs.getBoolean("allowChanges", true);
            if (allowChanges){
                if ((Integer.parseInt(mins)%15)==0){

                    if (meetPrior&& MeetingsUpdates.MEET_STATUS){
                        MeetingsUpdates mu = new MeetingsUpdates();
                        mu.readA();

                        if (!chngOnce){
                            if (Globals.COL_VOLUME != Globals.METRIC_VOLUME){
                                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                        Globals.METRIC_VOLUME,AudioManager.FLAG_PLAY_SOUND);
                            }

                            if (Globals.COL_RING != Globals.METRIC_RING){
                                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

                                audioManager.setRingerMode(Globals.METRIC_RING);
                            }

                            if (Globals.COL_BRIGHT != Globals.METRIC_BRIGHT){
                                // The service is being created
                                // set SCREEN_BRIGHTNESS
                                android.provider.Settings.System.putInt(getContentResolver(),
                                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

                                android.provider.Settings.System.putInt(getContentResolver(),
                                        android.provider.Settings.System.SCREEN_BRIGHTNESS,
                                        Globals.COL_BRIGHT);
                                /// start new Activity
                                Intent intent = new Intent(getBaseContext(), DummyBrightnessActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("brightness value", Globals.METRIC_BRIGHT);
                                getApplication().startActivity(intent);

                                chngOnce = true;
                            }
                        }

                    }else {
                        CheckMetrics cm = new CheckMetrics();
                        cm.init();

                        if (!chngOnce){

                            if (CheckMetrics.TOTAL_METRICS>2){

                                if (Globals.COL_VOLUME != Globals.METRIC_VOLUME){
                                    AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

                                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                            Globals.METRIC_VOLUME,AudioManager.FLAG_PLAY_SOUND);
                                }

                                if (Globals.COL_RING != Globals.METRIC_RING){
                                    AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

                                    audioManager.setRingerMode(Globals.METRIC_RING);
                                }

                                if (Globals.COL_BRIGHT != Globals.METRIC_BRIGHT){
                                    // The service is being created
                                    // set SCREEN_BRIGHTNESS
                                    android.provider.Settings.System.putInt(getContentResolver(),
                                            Settings.System.SCREEN_BRIGHTNESS_MODE,
                                            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

                                    android.provider.Settings.System.putInt(getContentResolver(),
                                            android.provider.Settings.System.SCREEN_BRIGHTNESS,
                                            Globals.COL_BRIGHT);
                                    /// start new Activity
                                    Intent intent = new Intent(getBaseContext(), DummyBrightnessActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("brightness value", Globals.METRIC_BRIGHT);
                                    getApplication().startActivity(intent);

                                    chngOnce = true;
                                }
                            }

                        }
                        CheckMetrics.TOTAL_METRICS = 0;
                    }
                }
            }

            if (lightSensor == null) {
                Toast.makeText(this, "The device has no light sensor !", Toast.LENGTH_SHORT).show();
                Globals.CUR_BRI = "0";
            }else {
                if ((Integer.parseInt(mins)%3)==2){
                    maxValue = lightSensor.getMaximumRange();

                    //Log.d("RAWDATA","luz:"+Double.toString(maxValue));

                    lightEventListener = new SensorEventListener() {
                        @Override
                        public void onSensorChanged(SensorEvent sensorEvent) {

                            float value = sensorEvent.values[0];

                            //Log.d("RAWDATA","luz2:"+Float.toString(value));

                            totalBrilho += value;
                            totalTestesBri ++;

                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int i) {

                        }
                    };

                    sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
                }
            }

            //TODO: '0(3) mins
            if ((Integer.parseInt(mins)%3)==0||Integer.parseInt(mins)==0){
                //Clean Local Data Before
                if (totalTestesBri==0){
                    Globals.CUR_BRI = "0.0";
                }else {
                    Globals.CUR_BRI = Double.toString(totalBrilho/totalTestesBri);
                }

                totalBrilho = 0;
                totalTestesBri = 0;

                if (totalTestesVol==0){
                    localvol = "0.0";
                }else {
                    localvol = Double.toString(totalVolume/totalTestesVol);
                }

                //LAT LONG DATA
                LocationCollect lc = new LocationCollect();
                lc.collectLatitude(this);
                latitude = lc.getLatitude();
                longitude = lc.getLongitude();

                //BRIGHT DATA
                BrightnessCollect bc = new BrightnessCollect();
                bright = bc.checkBrightness(this);

                //VOLUME DATA
                VolumeCollect vc = new VolumeCollect();
                volume = vc.checkVolume(this);

                //BATTERY POWER SAVE MODE DATA
                powerSave = Boolean.toString(BatteryCollect.couldBePowerSaveMode(this));

                //RINGMODE DATA
                RingmodeCollect rc = new RingmodeCollect();
                ringMode = rc.checkRingmode(this);

                if (!readOnce){
                    startCollect();
                    readOnce = true;
                }
            }
        }
    }

    public void startCollect(){

        PlacesStorage ps = new PlacesStorage();

        FileInputStream is;
        BufferedReader reader;
        final File file = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Places/"+ "/" + "listPlaces.txt");

        try{
            int numLine = 0;
            if (file.exists()) {
                is = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    String[] fileLocation = line.split(",");
                    String fileLat = fileLocation[0];
                    String fileLong = fileLocation[1];

                    if ((Double.parseDouble(latitude)-Double.parseDouble(fileLat)<0.0002)&&(Double.parseDouble(latitude)-Double.parseDouble(fileLat)>-0.0002)){
                        if ((Double.parseDouble(longitude)-Double.parseDouble(fileLong)<0.0002)&&(Double.parseDouble(longitude)-Double.parseDouble(fileLong)>-0.0002)){
                            fileFound = true;
                            Globals.LINE_LOCATION = Integer.toString(numLine);

                            sendAll();
                        }
                    }
                    numLine++;
                    line = reader.readLine();
                }
                if (!fileFound){
                    ps.createFile(latitude,longitude);
                    Globals.LINE_LOCATION = Integer.toString(numLine);

                    sendAll();
                }
            }
            else {
                ps.createFile(latitude,longitude);
                Globals.LINE_LOCATION = Integer.toString(numLine);

                sendAll();
            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    public void sendAll(){
        //Save Time
        DateTimeCollect dtc = new DateTimeCollect();
        Globals.LINE_TIME = dtc.LINE_TIME();

        //Save Volume
        VolumeCollect vc = new VolumeCollect();
        Globals.LINE_VOLUME = vc.checkVolume(this);

        //Save Bright
        BrightnessCollect bc = new BrightnessCollect();
        Globals.LINE_BRIGHT = bc.checkBrightness(this);

        //Save Ringmode
        RingmodeCollect rc = new RingmodeCollect();
        Globals.LINE_RING = rc.checkRingmode(this);

        //Save Battery
        int battCorrected = (int) level/5;
        Globals.LINE_BATTERY = Integer.toString(battCorrected);
        Globals.LINE_CHARGING = Integer.toString(plugged);
        Globals.LINE_SAVING = powerSave;

        //Save Locals
        int newVol = (int) Double.parseDouble(localvol)/5;
        Globals.LINE_LOCALVOL = Integer.toString(newVol);
        int newBri = (int) Double.parseDouble(Globals.CUR_BRI)/5;
        Globals.LINE_LOCALBRI = Integer.toString(newBri);

        totalTestesVol = 0;
        totalVolume = 0;

        //Save Connection
        if (ConnectionCollect.isConnectedToInternet(this)){
            ConnectivityManager cm =
                    (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Globals.LINE_CONNECTION = "2"; //WIFI
            }else {
                Globals.LINE_CONNECTION = "1"; //MOBILE
            }
        }else {
            Globals.LINE_CONNECTION = "0"; //OFF
        }

        //Save Bluetooth
        BluetoothCollect blc = new BluetoothCollect();
        if (!blc.checkBluetooth().equals("null")){
            Globals.LINE_BLUETOOTH = blc.checkBluetooth();
        }

        //Save Headphone
        HeadphoneCollect hc = new HeadphoneCollect();
        Globals.LINE_HEADPHONE = hc.checkHeadphone(this);

        ADB adb = new ADB();
        adb.createADB();
        BDB bdb = new BDB();
        bdb.createBDB();
        CDB cdb = new CDB();
        cdb.createDB();
        DDB ddb = new DDB();
        ddb.createDB();
        EDB edb = new EDB();
        edb.createDB();
        FDB fdb = new FDB();
        fdb.createDB();
        GDB gdb = new GDB();
        gdb.createDB();
        HDB hdb = new HDB();
        hdb.createDB();

    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        }
    };
}
