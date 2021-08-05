package com.android.trovi.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.trovi.Models.UserModel;
import com.android.trovi.R;
import com.android.trovi.Utils.Globals;
import com.android.trovi.Utils.Permissions;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class SignInActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference table_user;

    private String[] permissoesNecessarias;

    EditText signin_03,signin_04;
    TextView signin_01,signin_02,signin_05;
    TextView signin_06,signin_07,signin_08_1,signin_09,signin_10;
    LinearLayout signin_08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.white));

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
            permissoesNecessarias = new String[]{
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
            };
        }else {
            permissoesNecessarias = new String[]{
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };
        }

        database = FirebaseDatabase.getInstance("https://trovi-fb71e-default-rtdb.firebaseio.com/");
        table_user = database.getReference("Users");

        Paper.init(this);

        if (!Settings.canDrawOverlays(this)) {
            //Request permission if not authorized
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 0);
        }

        if (!Settings.System.canWrite(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            startActivity(intent);
        }

        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        // Check if the notification policy access has been granted for the app.
        if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }

        Permissions.validaPermissoes(1,SignInActivity.this,permissoesNecessarias);

        initAll();

        signin_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final @NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(signin_03.getText().toString()).exists()){
                            final UserModel user = dataSnapshot.child(signin_03.getText().toString()).getValue(UserModel.class);
                            if(signin_04.getText().toString().equals(user.getPassword())){
                                Paper.book().write(Globals.USER_KEY, signin_03.getText().toString());
                                Paper.book().write(Globals.PWD_KEY, signin_04.getText().toString());

                                Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
                                Globals.CURRENT_USER = user;
                                startActivity(homeIntent);
                                finish();
                            }
                        }else {
                            Toast.makeText(SignInActivity.this, "Este número ainda não possui cadastro no Trovi", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        signin_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAll(){
        Typeface poppins_semibold = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-SemiBold.ttf");
        Typeface poppins_regular = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-Regular.ttf");

        signin_01 = findViewById(R.id.signin_01);
        signin_02 = findViewById(R.id.signin_02);
        signin_03 = findViewById(R.id.signin_03);
        signin_04 = findViewById(R.id.signin_04);
        signin_05 = findViewById(R.id.signin_05);
        signin_06 = findViewById(R.id.signin_06);
        signin_07 = findViewById(R.id.signin_07);
        signin_08 = findViewById(R.id.signin_08);
        signin_08_1 = findViewById(R.id.signin_08_1);
        signin_09 = findViewById(R.id.signin_09);
        signin_10 = findViewById(R.id.signin_10);

        signin_01.setTypeface(poppins_semibold);
        signin_02.setTypeface(poppins_regular);
        signin_03.setTypeface(poppins_regular);
        signin_04.setTypeface(poppins_regular);
        signin_05.setTypeface(poppins_regular);
        signin_06.setTypeface(poppins_regular);
        signin_07.setTypeface(poppins_regular);
        signin_08_1.setTypeface(poppins_regular);
        signin_09.setTypeface(poppins_regular);
        signin_10.setTypeface(poppins_regular);
    }

    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults){

        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        for (int resultado:grantResults){
            if (resultado== PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }

    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilizar este app, é necessário aceitar as permissões");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
