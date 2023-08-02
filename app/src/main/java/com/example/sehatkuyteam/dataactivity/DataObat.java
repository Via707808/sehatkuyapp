package com.example.sehatkuyteam.dataactivity;

public class DataObat {

    String idObat, gambarObat, namaObat, hargaPer, harga, dibeli, kategori, diskon;

    public DataObat() {

    }

    public DataObat(String idObat, String gambarObat, String namaObat, String hargaPer, String harga, String dibeli, String kategori, String diskon) {
        this.idObat = idObat;
        this.gambarObat = gambarObat;
        this.namaObat = namaObat;
        this.hargaPer = hargaPer;
        this.harga = harga;
        this.dibeli = dibeli;
        this.kategori = kategori;
        this.diskon = diskon;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getGambarObat() {
        return gambarObat;
    }

    public void setGambarObat(String gambarObat) {
        this.gambarObat = gambarObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getHargaPer() {
        return hargaPer;
    }

    public void setHargaPer(String hargaPer) {
        this.hargaPer = hargaPer;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDibeli() {
        return dibeli;
    }

    public void setDibeli(String dibeli) {
        this.dibeli = dibeli;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }
}
