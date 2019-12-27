package com.chickennoodleteam.kantinamikom;

public class Basket {
    String nama;
    String jumlah_pesanan;

    public Basket() {
    }

    public Basket(String nama, String harga_dibayar, String jumlah_pesanan) {
        this.nama = nama;
        this.jumlah_pesanan = jumlah_pesanan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getJumlah_pesanan() {
        return jumlah_pesanan;
    }

    public void setJumlah_pesanan(String jumlah_pesanan) {
        this.jumlah_pesanan = jumlah_pesanan;
    }
}
