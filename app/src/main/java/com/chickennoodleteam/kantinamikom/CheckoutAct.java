package com.chickennoodleteam.kantinamikom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class CheckoutAct extends AppCompatActivity {
    LinearLayout btn_basket;
    Button btn_tambah, btn_kurang;
    TextView txt_angkasebelumDipesan, txt_angkaDipesan,txt_harga,txt_totalHarga,txt_checkout;
    ImageView img_checkout;
    Integer valuePesanan = 0;
    Integer valueHarga = 0;

    Integer valueTotalharga = 0;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    Integer nomor_transaksi = new Random().nextInt();

    DatabaseReference reference1,reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getUsernameLocal();

        Bundle bundle = getIntent().getExtras();
        final String jenis_makanan_baru = bundle.getString("jenis_makanan");

        //button
        btn_basket = findViewById(R.id.btn_basket);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_kurang = findViewById(R.id.btn_kurang);

        btn_kurang.setEnabled(false);

        img_checkout = findViewById(R.id.img_checkout);

        //Integer
        txt_angkasebelumDipesan = findViewById(R.id.txt_angkasebelumDipesan);
        txt_angkaDipesan = findViewById(R.id.txt_angkaDipesan);
        txt_totalHarga = findViewById(R.id.txt_totalHarga);
        txt_harga = findViewById(R.id.txt_harga);
        txt_checkout = findViewById((R.id.txt_checkout));

        //setting value
        txt_angkasebelumDipesan.setText(valuePesanan.toString());
        txt_angkaDipesan.setText(valuePesanan.toString());
        //txt_harga.setText("Rp." + valueHarga);


        txt_totalHarga.setText("RP."+valueTotalharga+"");

        reference1 = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child(jenis_makanan_baru);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_checkout.setText(dataSnapshot.child("nama").getValue().toString());
                txt_harga.setText("Rp. "+ dataSnapshot.child("harga").getValue().toString());
                valueHarga = Integer.valueOf(dataSnapshot.child("harga").getValue().toString());
                valueTotalharga = valueHarga * valuePesanan;
                txt_totalHarga.setText("RP."+valueTotalharga+"");
                Picasso.with(CheckoutAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_checkout);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //tombol Tambah Pesanan
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuePesanan += 1;
                txt_angkasebelumDipesan.setText(valuePesanan.toString());
                txt_angkaDipesan.setText(valuePesanan.toString());
                if (valuePesanan > 0){
                    btn_kurang.setEnabled(true);
                }

                valueTotalharga = valueHarga * valuePesanan;
                txt_totalHarga.setText("RP."+valueTotalharga+"");
            }
        });

        //tombol Kurang Pesanan
        btn_kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuePesanan -= 1;
                txt_angkasebelumDipesan.setText(valuePesanan.toString());
                txt_angkaDipesan.setText(valuePesanan.toString());
                if (valuePesanan <= 0) {
                    btn_kurang.setEnabled(false);
                }
                valueTotalharga = valueHarga * valuePesanan;
                txt_totalHarga.setText("Rp."+valueTotalharga+"");
            }
        });



        btn_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Simpan database
                reference2 = FirebaseDatabase.getInstance().getReference().child("Dipesan").child(username_key_new).child(txt_checkout.getText().toString() + nomor_transaksi);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference2.getRef().child("nama").setValue(txt_checkout.getText().toString());
                        reference2.getRef().child("jumlah_pesanan").setValue(valuePesanan);
                        reference2.getRef().child("harga_dibayar").setValue(valueTotalharga);

                        Intent gotobasket = new Intent(CheckoutAct.this, BasketAct.class);
                        startActivity(gotobasket);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent gotobasket = new Intent(CheckoutAct.this, BasketAct.class);
                startActivity(gotobasket);
            }
        });



    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
