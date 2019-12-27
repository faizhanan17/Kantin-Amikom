package com.chickennoodleteam.kantinamikom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class CheckoutAct extends AppCompatActivity {
    LinearLayout btn_bayar;
    Button btn_tambah, btn_kurang;
    TextView txt_angkasebelumDipesan, txt_harga,txt_totalHarga,txt_checkout,txt_saldo;
    ImageView img_checkout;
    Integer pembayaran = 0;
    Integer valuePesanan = 1;
    Integer valueHarga = 0;
    Integer total_pembayaran = 0;

    Integer my_balance = 0;
    Integer valueTotalharga = 0;


    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    Integer nomor_transaksi = new Random().nextInt();

    DatabaseReference reference1,reference2,reference3,reference5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getUsernameLocal();

        Bundle bundle = getIntent().getExtras();
        final String jenis_makanandanminuman_baru = bundle.getString("jenis_makanan_dan_minuman");

        //button
        btn_bayar = findViewById(R.id.btn_bayar);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_kurang = findViewById(R.id.btn_kurang);

        btn_kurang.setEnabled(false);

        img_checkout = findViewById(R.id.img_checkout);

        //Integer
        txt_angkasebelumDipesan = findViewById(R.id.txt_angkasebelumDipesan);
        txt_totalHarga = findViewById(R.id.txt_totalharga);
        txt_harga = findViewById(R.id.txt_harga);
        txt_saldo = findViewById(R.id.txt_saldo);
        txt_checkout = findViewById((R.id.txt_checkout));


        //setting value
        txt_angkasebelumDipesan.setText(valuePesanan.toString());


        txt_totalHarga.setText("Rp."+valueTotalharga+"");

        reference1 = FirebaseDatabase.getInstance().getReference().child("MakanandanMinuman").child(jenis_makanandanminuman_baru);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_checkout.setText(dataSnapshot.child("nama").getValue().toString());
                txt_harga.setText("Rp. "+ dataSnapshot.child("harga").getValue().toString());
                valueHarga = Integer.valueOf(dataSnapshot.child("harga").getValue().toString());
                valueTotalharga = valueHarga * valuePesanan;
                txt_totalHarga.setText("Rp."+valueTotalharga+"");
                Picasso.with(CheckoutAct.this).load(dataSnapshot.child("url_makanan").getValue().toString()).centerCrop().fit().into(img_checkout);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                my_balance = Integer.valueOf(dataSnapshot.child("saldo").getValue().toString());
                txt_saldo.setText("Rp. " + my_balance +"");
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
                if (valuePesanan > 1){
                    btn_kurang.setEnabled(true);
                }

                valueTotalharga = valueHarga * valuePesanan;
                txt_totalHarga.setText("Rp."+valueTotalharga+"");
                if (valueTotalharga > my_balance){
                    btn_bayar.animate().translationY(300).alpha(0).setDuration(350).start();
                    btn_bayar.setEnabled(false);
                    txt_saldo.setTextColor(Color.parseColor("#c70202"));
                }
            }
        });

        //tombol Kurang Pesanan
        btn_kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuePesanan -= 1;
                txt_angkasebelumDipesan.setText(valuePesanan.toString());
                if (valuePesanan <= 2) {
                    btn_kurang.setEnabled(false);
                }
                valueTotalharga = valueHarga * valuePesanan;
                txt_totalHarga.setText("Rp."+valueTotalharga+"");
                if (valueTotalharga < my_balance){
                    btn_bayar.animate().translationY(0).alpha(1).setDuration(350).start();
                    btn_bayar.setEnabled(true);
                    txt_saldo.setTextColor(Color.parseColor("#1d7df2"));
                }
            }
        });



        btn_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Simpan database
                reference3 = FirebaseDatabase.getInstance().getReference().child("Dipesan").child(username_key_new).child(txt_checkout.getText().toString() + nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("id_makanan").setValue(txt_checkout.getText().toString() + nomor_transaksi);
                        reference3.getRef().child("nama").setValue(txt_checkout.getText().toString());
                        reference3.getRef().child("jumlah_pesanan").setValue(valuePesanan.toString());


                        Toast.makeText(getApplicationContext(), "Berhasil memasukan ke Keranjang :)", Toast.LENGTH_SHORT).show();

                        Intent gotohome = new Intent(CheckoutAct.this, HomeAct.class);
                        startActivity(gotohome);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reference5 = FirebaseDatabase.getInstance().getReference().child("Pembayaran").child(username_key_new);
                reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                                pembayaran = Integer.valueOf(dataSnapshot.child("harga_dibayar").getValue().toString());
                                pembayaran = pembayaran + valueTotalharga;
                                reference5.getRef().child("harga_dibayar").setValue(pembayaran);
                        }
                        else {
                            reference5.getRef().child("harga_dibayar").setValue(valueTotalharga);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                Intent gotohome = new Intent(CheckoutAct.this, HomeAct.class);
                startActivity(gotohome);
                

            }
        });





    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
