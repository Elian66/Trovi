package com.android.trovi.Screens;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.trovi.R;
import com.android.trovi.Utils.Globals;

public class EditProfileActivity extends AppCompatActivity {

    ImageView back;
    EditText name, mail;
    TextView ename,email,epass;
    LinearLayout image;
    RelativeLayout password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initAll();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initAll(){
        Typeface poppins_semibold = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-SemiBold.ttf");
        Typeface poppins_regular = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-Regular.ttf");

        back = findViewById(R.id.edit_back);
        image = findViewById(R.id.edit_image);
        name = findViewById(R.id.edit_name_edt);
        mail = findViewById(R.id.edit_mail_edt);
        ename = findViewById(R.id.edit_name_txt);
        email = findViewById(R.id.edit_mail_txt);
        password = findViewById(R.id.edit_password);
        epass = findViewById(R.id.edit_password_txt);
        button = findViewById(R.id.edit_button);

        name.setTypeface(poppins_regular);
        ename.setTypeface(poppins_regular);
        mail.setTypeface(poppins_regular);
        email.setTypeface(poppins_regular);
        epass.setTypeface(poppins_regular);
        button.setTypeface(poppins_regular);

        if (Globals.CURRENT_USER!=null){
            name.setText(Globals.CURRENT_USER.getName());
            mail.setText(Globals.CURRENT_USER.getMail());
        }
    }
}