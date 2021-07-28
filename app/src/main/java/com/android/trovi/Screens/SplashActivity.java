package com.android.trovi.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.trovi.Models.UserModel;
import com.android.trovi.R;
import com.android.trovi.Utils.Globals;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.background));

        startNextActivity();
    }

    private void startNextActivity(){
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        final boolean firstStart = prefs.getBoolean("firstStart", true);

        Paper.init(this);

        String user = Paper.book().read(Globals.USER_KEY);
        if (user != null) {
            if(!user.isEmpty())
            {
                login(user);
            }

        }else {
            Handler handle = new Handler();
            handle.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (firstStart){
                        Intent intent = new Intent(SplashActivity.this, OnboardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(SplashActivity.this,SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 3000);
        }
    }

    private void login(final String phone) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://trovi-fb71e-default-rtdb.firebaseio.com/");
        final DatabaseReference table_user = database.getReference("Users");

        table_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final @NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(phone).exists()){

                    final UserModel user = dataSnapshot.child(phone).getValue(UserModel.class);
                    Intent homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    Globals.CURRENT_USER = user;
                    startActivity(homeIntent);
                    finish();
                }else {
                    Toast.makeText(SplashActivity.this, "Este número ainda não possui cadastro no Trovi", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
