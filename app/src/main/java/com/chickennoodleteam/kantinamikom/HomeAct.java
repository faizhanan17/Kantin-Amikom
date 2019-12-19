package com.chickennoodleteam.kantinamikom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {
    LinearLayout btn_food,btn_drink, btn_snack;

    TextView namahome,nimhome,saldohome;
    ImageView picprofile;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        //Button
        btn_food = findViewById(R.id.btn_food);
        btn_drink = findViewById(R.id.btn_drink);
        btn_snack = findViewById(R.id.btn_snack);

        //TextView
        namahome = findViewById(R.id.namahome);
        nimhome = findViewById(R.id.nimhome);
        saldohome = findViewById(R.id.saldohome);
        picprofile = findViewById(R.id.picprofile);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namahome.setText(dataSnapshot.child("nama").getValue().toString());
                nimhome.setText(dataSnapshot.child("nim").getValue().toString());
                saldohome.setText("Rp. " + dataSnapshot.child("saldo").getValue().toString());
                Picasso.with(HomeAct.this).load(dataSnapshot.child("url_photo").getValue().toString()).centerCrop().fit().into(picprofile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotofood = new Intent(HomeAct.this, FoodAct.class);
                startActivity(gotofood);
            }
        });
        btn_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodrink = new Intent(HomeAct.this, DrinkAct.class);
                startActivity(gotodrink);
            }
        });
        btn_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosnack = new Intent (HomeAct.this, SnackAct.class);
                startActivity(gotosnack);
            }
        });
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
