package com.chickennoodleteam.kantinamikom;

public class Basket {
    String nama;
    Integer harga_dibayar,jumlah_pesanan;

    public Basket(String nama, Integer harga_dibayar, Integer jumlah_pesanan) {
        this.nama = nama;
        this.harga_dibayar = harga_dibayar;
        this.jumlah_pesanan = jumlah_pesanan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getHarga_dibayar() {
        return harga_dibayar;
    }

    public void setHarga_dibayar(Integer harga_dibayar) {
        this.harga_dibayar = harga_dibayar;
    }

    public Integer getJumlah_pesanan() {
        return jumlah_pesanan;
    }

    public void setJumlah_pesanan(Integer jumlah_pesanan) {
        this.jumlah_pesanan = jumlah_pesanan;
    }
}
