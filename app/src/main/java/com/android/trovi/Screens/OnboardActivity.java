package com.android.trovi.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.trovi.R;

public class OnboardActivity extends AppCompatActivity {

    LinearLayout boardlay_01,boardlay_02,boardlay_03;
    TextView onboard_01,onboard_02,onboard_03;
    TextView onboard_04,onboard_05;
    TextView onboard_06,onboard_07,onboard_08;
    Integer current_screen;
    ImageView steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.background));

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.putBoolean("firstCollect", true);
        editor.putBoolean("firstChanges", true);
        editor.putBoolean("allowCollect", false);
        editor.putBoolean("allowChanges", false);
        editor.apply();

        initAll();

        current_screen = 1;

        //Buttons actions
        onboard_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnboardActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        onboard_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_screen==1){
                    current_screen=2;
                    boardlay_01.setVisibility(View.GONE);
                    boardlay_02.setVisibility(View.VISIBLE);
                    boardlay_03.setVisibility(View.GONE);
                    onboard_02.setVisibility(View.VISIBLE);
                    onboard_03.setVisibility(View.VISIBLE);
                    steps.setImageResource(R.drawable.ic_steps_b);
                }
                else if (current_screen==2){
                    current_screen=3;
                    boardlay_01.setVisibility(View.GONE);
                    boardlay_02.setVisibility(View.GONE);
                    boardlay_03.setVisibility(View.VISIBLE);
                    onboard_02.setVisibility(View.GONE);
                    onboard_03.setVisibility(View.GONE);
                    steps.setImageResource(R.drawable.ic_steps_c);
                }
            }
        });

        onboard_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnboardActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initAll(){
        Typeface poppins_semibold = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-SemiBold.ttf");
        Typeface poppins_regular = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-Regular.ttf");

        boardlay_01 = findViewById(R.id.boardlay_01);
        boardlay_02 = findViewById(R.id.boardlay_02);
        boardlay_03 = findViewById(R.id.boardlay_03);

        onboard_01 = findViewById(R.id.onboard_01);
        onboard_02 = findViewById(R.id.onboard_02);
        onboard_03 = findViewById(R.id.onboard_03);
        onboard_04 = findViewById(R.id.onboard_04);
        onboard_05 = findViewById(R.id.onboard_05);
        onboard_06 = findViewById(R.id.onboard_06);
        onboard_07 = findViewById(R.id.onboard_07);
        onboard_08 = findViewById(R.id.onboard_08);

        onboard_01.setTypeface(poppins_semibold);
        onboard_02.setTypeface(poppins_regular);
        onboard_03.setTypeface(poppins_regular);
        onboard_04.setTypeface(poppins_semibold);
        onboard_05.setTypeface(poppins_regular);
        onboard_06.setTypeface(poppins_regular);
        onboard_07.setTypeface(poppins_regular);
        onboard_08.setTypeface(poppins_regular);

        boardlay_01.setVisibility(View.VISIBLE);
        boardlay_02.setVisibility(View.GONE);
        boardlay_03.setVisibility(View.GONE);
        onboard_02.setVisibility(View.VISIBLE);
        onboard_03.setVisibility(View.VISIBLE);

        steps = findViewById(R.id.steps_src);
        steps.setImageResource(R.drawable.ic_steps_a);
    }
}
