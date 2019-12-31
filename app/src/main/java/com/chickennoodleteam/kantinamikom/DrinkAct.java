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

public class DrinkAct extends AppCompatActivity {
    LinearLayout btn_teh,btn_jeruk,btn_kopi,btn_goodday,btn_escampur,btn_esteh,btn_esjeruk,btn_esgoodday;
    ImageView img_teh,img_jeruk,img_kopi,img_goodday,img_escampur,img_esteh,img_esjeruk,img_esgoodday;
    TextView txt_teh,txt_jeruk,txt_kopi,txt_goodday,txt_escampur,txt_esteh,txt_esjeruk,txt_esgoodday,
            txt_hargateh,txt_hargajeruk,txt_hargakopi,txt_hargagoodday,txt_hargaescampur,txt_hargaesteh,txt_hargaesjeruk,txt_hargaesgoodday;

    DatabaseReference referenceteh,referencejeruk,
            referencekopi,referencegoodday,referenceescampur,referenceesteh,
            referenceesjeruk,referenceesgoodday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Button
        btn_teh = findViewById(R.id.btn_teh);
        btn_jeruk = findViewById(R.id.btn_jeruk);
        btn_kopi = findViewById(R.id.btn_kopi);
        btn_goodday = findViewById(R.id.btn_goodday);
        btn_esteh = findViewById(R.id.btn_esteh);
        btn_esjeruk = findViewById(R.id.btn_esjeruk);
        btn_esgoodday = findViewById(R.id.btn_esgoodday);
        btn_escampur = findViewById(R.id.btn_escampur);

        //Image
        img_teh = findViewById(R.id.img_teh);
        img_jeruk = findViewById(R.id.img_jeruk);
        img_kopi = findViewById(R.id.img_kopi);
        img_goodday = findViewById(R.id.img_goodday);
        img_esteh = findViewById(R.id.img_esteh);
        img_esjeruk = findViewById(R.id.img_esjeruk);
        img_esgoodday = findViewById(R.id.img_esgoodday);
        img_escampur = findViewById(R.id.img_escampur);

        //TextView
        txt_teh = findViewById(R.id.txt_teh);
        txt_jeruk = findViewById(R.id.txt_jeruk);
        txt_kopi = findViewById(R.id.txt_kopi);
        txt_goodday = findViewById(R.id.txt_goodday);
        txt_esteh = findViewById(R.id.txt_esteh);
        txt_esjeruk = findViewById(R.id.txt_esjeruk);
        txt_esgoodday = findViewById(R.id.txt_esgoodday);
        txt_escampur = findViewById(R.id.txt_escampur);

        //TextView harga
        txt_hargateh = findViewById(R.id.txt_hargateh);
        txt_hargajeruk = findViewById(R.id.txt_hargajeruk);
        txt_hargakopi = findViewById(R.id.txt_hargakopi);
        txt_hargagoodday = findViewById(R.id.txt_hargagoodday);
        txt_hargaesteh = findViewById(R.id.txt_hargaesteh);
        txt_hargaesjeruk = findViewById(R.id.txt_hargaesjeruk);
        txt_hargaesgoodday = findViewById(R.id.txt_hargaesgoodday);
        txt_hargaescampur = findViewById(R.id.txt_hargaescampur);

        //Ambil data
        referenceteh = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("teh");
        referenceteh.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_teh.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargateh.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_teh);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        referencejeruk = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("jeruk");
        referencejeruk.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_jeruk.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargajeruk.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_jeruk);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        referencekopi = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("kopi");
        referencekopi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_kopi.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargakopi.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_kopi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        referencegoodday = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("goodday");
        referencegoodday.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_goodday.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargagoodday.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_goodday);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        referenceesteh = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("esteh");
        referenceesteh.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_esteh.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargaesteh.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_esteh);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        referenceesjeruk = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("esjeruk");
        referenceesjeruk.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_esjeruk.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargaesjeruk.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_esjeruk);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        referenceesgoodday = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("esgoodday");
        referenceesgoodday.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_esgoodday.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargaesgoodday.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_esgoodday);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        referenceescampur = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child("escampur");
        referenceescampur.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_escampur.setText(dataSnapshot.child("nama").getValue().toString());
                txt_hargaescampur.setText("Rp. " + dataSnapshot.child("harga").getValue().toString());
                Picasso.with(DrinkAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_escampur);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Button Fungsi
        btn_teh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","teh");
                startActivity(gotocheckout);
            }
        });
        btn_jeruk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","jeruk");
                startActivity(gotocheckout);
            }
        });
        btn_kopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","kopi");
                startActivity(gotocheckout);
            }
        });
        btn_goodday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","goodday");
                startActivity(gotocheckout);
            }
        });
        btn_esteh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","esteh");
                startActivity(gotocheckout);
            }
        });
        btn_esjeruk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","esjeruk");
                startActivity(gotocheckout);
            }
        });
        btn_esgoodday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","esgoodday");
                startActivity(gotocheckout);
            }
        });
        btn_escampur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(DrinkAct.this,CheckoutAct.class);
                gotocheckout.putExtra("jenis_makanan_dan_minuman","escampur");
                startActivity(gotocheckout);
            }
        });



    }
}
