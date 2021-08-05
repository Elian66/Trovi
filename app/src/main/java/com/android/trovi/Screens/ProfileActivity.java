package com.android.trovi.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.trovi.R;
import com.android.trovi.Utils.Globals;

import io.paperdb.Paper;

public class ProfileActivity extends AppCompatActivity {

    ImageView back,image;
    TextView name,account,preferences,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Paper.init(this);

        Typeface poppins_semibold = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-SemiBold.ttf");
        Typeface poppins_regular = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-Regular.ttf");

        back = findViewById(R.id.profile_back);
        image = findViewById(R.id.profile_image);
        name = findViewById(R.id.profile_name);
        account = findViewById(R.id.profile_account);
        preferences = findViewById(R.id.profile_preferences);
        exit = findViewById(R.id.profile_exit);

        name.setTypeface(poppins_semibold);
        account.setTypeface(poppins_regular);
        preferences.setTypeface(poppins_regular);
        exit.setTypeface(poppins_regular);

        name.setText(Globals.CURRENT_USER.getName());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();

                Intent signIn = new Intent(ProfileActivity.this, SignInActivity.class);
                signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(signIn);
            }
        });

    }
}
