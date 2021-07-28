package com.android.trovi.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.trovi.Models.UserModel;
import com.android.trovi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference table_user;

    TextView signup_01;
    EditText signup_02,signup_03,signup_04,signup_05;
    TextView signup_06,signup_07,signup_08_1,signup_09,signup_10;
    LinearLayout signup_08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initAll();

        database = FirebaseDatabase.getInstance("https://trovi-fb71e-default-rtdb.firebaseio.com/");
        table_user = database.getReference("Users");

        signup_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final @NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(signup_04.getText().toString()).exists()){
                            Toast.makeText(SignUpActivity.this, "Este número já está cadastrado no Trovi", Toast.LENGTH_SHORT).show();
                        }else {
                            UserModel user = new UserModel(signup_02.getText().toString(),
                                    signup_03.getText().toString(),
                                    signup_04.getText().toString(),
                                    signup_05.getText().toString());
                            table_user.child(signup_04.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();



                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        signup_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initAll(){
        Typeface poppins_semibold = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-SemiBold.ttf");
        Typeface poppins_regular = Typeface.createFromAsset(this.getAssets(), "fonts/Poppins-Regular.ttf");

        signup_01 = findViewById(R.id.signup_01);
        signup_02 = findViewById(R.id.signup_02);
        signup_03 = findViewById(R.id.signup_03);
        signup_04 = findViewById(R.id.signup_04);
        signup_05 = findViewById(R.id.signup_05);
        signup_06 = findViewById(R.id.signup_06);
        signup_07 = findViewById(R.id.signup_07);
        signup_08 = findViewById(R.id.signup_08);
        signup_08_1 = findViewById(R.id.signup_08_1);
        signup_09 = findViewById(R.id.signup_09);
        signup_10 = findViewById(R.id.signup_10);

        signup_01.setTypeface(poppins_semibold);
        signup_02.setTypeface(poppins_regular);
        signup_03.setTypeface(poppins_regular);
        signup_04.setTypeface(poppins_regular);
        signup_05.setTypeface(poppins_regular);
        signup_06.setTypeface(poppins_regular);
        signup_07.setTypeface(poppins_regular);
        signup_08_1.setTypeface(poppins_regular);
        signup_09.setTypeface(poppins_regular);
        signup_10.setTypeface(poppins_regular);
    }
}
