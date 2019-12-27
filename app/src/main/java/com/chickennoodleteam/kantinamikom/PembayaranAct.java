package com.chickennoodleteam.kantinamikom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class PembayaranAct extends AppCompatActivity {
    ImageView qr_code;
    TextView nomor_transaksi;

    Integer sisa_balance = 0;
    Integer my_balance = 0;
    Integer valueTotalharga = 0;
    Integer hapus_saldo;
    Integer enol = 0;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";



    DatabaseReference reference1,reference2,reference3,reference4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        getUsernameLocal();

        qr_code = findViewById(R.id.qrcode);
        nomor_transaksi = findViewById(R.id.nomor_transaksi);


        reference1 = FirebaseDatabase.getInstance().getReference().child("Pembayaran").child(username_key_new);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                valueTotalharga = Integer.valueOf(dataSnapshot.child("harga_dibayar").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        my_balance = Integer.valueOf(dataSnapshot.child("saldo").getValue().toString());
                        sisa_balance = my_balance - valueTotalharga;
                        reference2.getRef().child("saldo").setValue(sisa_balance);

                        Toast.makeText(getApplicationContext(), "Pembayaran berhasil, silahkan tunggu :)", Toast.LENGTH_SHORT).show();

                        Intent gotohome = new Intent(PembayaranAct.this,HomeAct.class);
                        startActivity(gotohome);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                reference3 = FirebaseDatabase.getInstance().getReference().child("Dipesan").child(username_key_new);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            reference3.removeValue();

                            Intent gotohome = new Intent(PembayaranAct.this, HomeAct.class);
                            startActivity(gotohome);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                reference4 = FirebaseDatabase.getInstance().getReference().child("Pembayaran").child(username_key_new);
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        hapus_saldo = Integer.valueOf(dataSnapshot.child("harga_dibayar").getValue().toString());
                        hapus_saldo = enol;
                        reference4.getRef().child("harga_dibayar").setValue(hapus_saldo);
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
