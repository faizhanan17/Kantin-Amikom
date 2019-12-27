package com.chickennoodleteam.kantinamikom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.PesananViewHolder> {

    Context context;
    ArrayList<Basket> basket;


    public AdapterPesanan (Context c, ArrayList<Basket>p){
       context = c;
       basket = p;
    }

    @NonNull
    @Override
    public PesananViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PesananViewHolder(LayoutInflater.from(context).inflate(R.layout.item_basket, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PesananViewHolder pesananViewHolder, int i) {
        pesananViewHolder.basket_nama.setText(basket.get(i).getNama());
        pesananViewHolder.banyak_pesanan.setText(basket.get(i).getJumlah_pesanan()+"X");
    }

    @Override
    public int getItemCount() {
        return basket.size();
    }

    class PesananViewHolder extends RecyclerView.ViewHolder{
        TextView basket_nama,banyak_pesanan;

        public PesananViewHolder(@NonNull View itemView) {
            super(itemView);

            basket_nama = itemView.findViewById(R.id.basket_nama);
            banyak_pesanan = itemView.findViewById(R.id.banyak_pesanan);

        }
    }
}
