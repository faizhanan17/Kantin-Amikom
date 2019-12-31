package com.chickennoodleteam.kantinamikom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInAct extends AppCompatActivity {
    Button btn_signin;
    EditText xnim,xpw;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_signin = findViewById(R.id.btn_signin);
        xnim =findViewById(R.id.xnim);
        xpw = findViewById(R.id.xpw);


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_signin.setEnabled(false);
                btn_signin.setText("Tunggu ...");

                final String nim = xnim.getText().toString();
                final String pw = xpw.getText().toString();

                if(nim.isEmpty()){
                    Toast.makeText(getApplicationContext(), "NIM Kosong !", Toast.LENGTH_SHORT).show();
                    btn_signin.setEnabled(true);
                    btn_signin.setText("SIGN IN");
                }
                else {
                    if (pw.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Password Kosong !", Toast.LENGTH_SHORT).show();
                        btn_signin.setEnabled(true);
                        btn_signin.setText("SIGN IN");
                    }
                    else{
                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(nim);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    //Toast.makeText(getApplicationContext(), "Selamat Datang :)", Toast.LENGTH_SHORT).show();
                                    //ambil data db

                                    String pwdariFirebase = dataSnapshot.child("pw").getValue().toString();
                                    if (pw.equals(pwdariFirebase)){

                                        //Simpan pada local username
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, xnim.getText().toString());
                                        editor.apply();

                                        Intent gotohome = new Intent(SignInAct.this, HomeAct.class);
                                        gotohome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(gotohome);

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_SHORT).show();
                                        btn_signin.setEnabled(true);
                                        btn_signin.setText("SIGN IN");

                                    }

                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "NIM tidak ada !", Toast.LENGTH_SHORT).show();
                                    btn_signin.setEnabled(true);
                                    btn_signin.setText("SIGN IN");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

            }
        });
    }

}
