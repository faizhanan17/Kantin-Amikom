package com.chickennoodleteam.kantinamikom;

import android.content.Context;
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
    public PesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PesananViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
