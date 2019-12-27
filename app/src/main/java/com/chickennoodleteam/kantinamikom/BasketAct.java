package com.chickennoodleteam.kantinamikom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BasketAct extends AppCompatActivity {
    RecyclerView tempat_pesanan;
    ArrayList<Basket> list;
    AdapterPesanan adapterPesanan;
    LinearLayout btn_clear,btn_bayar;
    TextView txt_saldobasket,txt_totalbayar_basket;

    Integer hapus_saldo;
    Integer enol = 0;
    Integer saldototal = 0;
    Integer hargatotal = 0;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";


    DatabaseReference reference,reference2,reference3,reference4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);
        getUsernameLocal();

        txt_saldobasket = findViewById(R.id.txt_saldobasket);
        txt_totalbayar_basket = findViewById(R.id.txt_totalbayar_basket);

        btn_clear = findViewById(R.id.btn_clear);
        btn_bayar = findViewById(R.id.btn_bayar);

        tempat_pesanan = findViewById(R.id.tempat_pesanan);
        tempat_pesanan.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Basket>();

        reference = FirebaseDatabase.getInstance().getReference().child("Dipesan").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Basket p = dataSnapshot1.getValue(Basket.class);
                    list.add(p);
                }
                adapterPesanan = new AdapterPesanan(BasketAct.this, list);
                tempat_pesanan.setAdapter(adapterPesanan);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference3 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                saldototal = Integer.valueOf(dataSnapshot.child("saldo").getValue().toString());
                txt_saldobasket.setText("Rp. "+ dataSnapshot.child("saldo").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference3 = FirebaseDatabase.getInstance().getReference().child("Pembayaran").child(username_key_new);
        reference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hargatotal = Integer.valueOf(dataSnapshot.child("harga_dibayar").getValue().toString());
                txt_totalbayar_basket.setText("Rp. " + dataSnapshot.child("harga_dibayar").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference2 = FirebaseDatabase.getInstance().getReference().child("Dipesan").child(username_key_new);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            if (saldototal > hargatotal) {
                                Intent gotopembayaran = new Intent(BasketAct.this, PembayaranAct.class);
                                startActivity(gotopembayaran);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Saldo anda kurang", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Anda belum pesan", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference2 = FirebaseDatabase.getInstance().getReference().child("Dipesan").child(username_key_new);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            reference2.removeValue();
                            Toast.makeText(getApplicationContext(), "Pesanan berhasil dibersihkan :)", Toast.LENGTH_SHORT).show();

                            Intent gotohome = new Intent(BasketAct.this, HomeAct.class);
                            startActivity(gotohome);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Tidak ada pesanan", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                reference3 = FirebaseDatabase.getInstance().getReference().child("Pembayaran").child(username_key_new);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        hapus_saldo = Integer.valueOf(dataSnapshot.child("harga_dibayar").getValue().toString());
                        hapus_saldo = enol;
                        reference3.getRef().child("harga_dibayar").setValue(hapus_saldo);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
