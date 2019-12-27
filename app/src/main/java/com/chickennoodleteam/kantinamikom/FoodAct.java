package com.chickennoodleteam.kantinamikom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodAct extends AppCompatActivity {
    LinearLayout btn_bakso,btn_pecel,btn_gadogado,btn_nasgor,btn_nasisayur,btn_mieayam;
    ImageView img_bakso,img_pecel,img_gadogado,img_nasgor,img_nasisayur,img_mieayam;
    TextView txt_bakso,txt_pecel,txt_gadogado,txt_nasgor,txt_nasisayur,txt_mieayam;



    DatabaseReference reference_bakso,reference_pecel,reference_gadogado,reference_nasgor,reference_nasisayur,reference_mieayam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //Button
        btn_bakso = findViewById(R.id.btn_bakso);
        btn_pecel = findViewById(R.id.btn_pecel);
        btn_gadogado = findViewById(R.id.btn_gadogado);
        btn_nasgor = findViewById(R.id.btn_nasgor);
        btn_nasisayur = findViewById(R.id.btn_nasisayur);
        btn_mieayam = findViewById(R.id.btn_mieayam);

        //ImageView
        img_bakso = findViewById(R.id.img_bakso);
        img_pecel = findViewById(R.id.img_pecel);
        img_gadogado = findViewById(R.id.img_gadogado);
        img_nasgor = findViewById(R.id.img_nasgor);
        img_nasisayur = findViewById(R.id.img_nasisayur);
        img_mieayam = findViewById(R.id.img_mieayam);

        //TextView
        txt_bakso = findViewById(R.id.txt_bakso);
        txt_pecel = findViewById(R.id.txt_pecel);
        txt_gadogado = findViewById(R.id.txt_gadogado);
        txt_nasgor = findViewById(R.id.txt_nasgor);
        txt_nasisayur = findViewById(R.id.txt_nasisayur);
        txt_mieayam = findViewById(R.id.txt_mieayam);

        //Ambil data
        reference_bakso = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("bakso");
        reference_bakso.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_bakso.setText(dataSnapshot.child("nama").getValue().toString());
                Picasso.with(FoodAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_bakso);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference_pecel = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("pecel");
        reference_pecel.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_pecel.setText(dataSnapshot.child("nama").getValue().toString());
                Picasso.with(FoodAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_pecel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference_nasgor = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("nasgor");
        reference_nasgor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_nasgor.setText(dataSnapshot.child("nama").getValue().toString());
                Picasso.with(FoodAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_nasgor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference_nasisayur = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("nasisayur");
        reference_nasisayur.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_nasisayur.setText(dataSnapshot.child("nama").getValue().toString());
                Picasso.with(FoodAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_nasisayur);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference_gadogado = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("gadogado");
        reference_gadogado.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_gadogado.setText(dataSnapshot.child("nama").getValue().toString());
                Picasso.with(FoodAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_gadogado);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference_mieayam = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("mieayam");
        reference_mieayam.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_mieayam.setText(dataSnapshot.child("nama").getValue().toString());
                Picasso.with(FoodAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_mieayam);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        //Button Fungsi
        btn_bakso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(FoodAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","bakso");
                startActivity(gotocheckout);
            }
        });

        btn_pecel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(FoodAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","pecel");
                startActivity(gotocheckout);
            }
        });

        btn_gadogado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(FoodAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","gadogado");
                startActivity(gotocheckout);
            }
        });


        btn_nasgor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(FoodAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","nasgor");
                startActivity(gotocheckout);
            }
        });

        img_nasisayur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(FoodAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","nasisayur");
                startActivity(gotocheckout);
            }
        });

        btn_mieayam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(FoodAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","mieayam");
                startActivity(gotocheckout);
            }
        });

    }
}
