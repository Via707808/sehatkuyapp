package com.example.sehatkuyteam.dataactivity;

public class DataTopik {
    String idTopik, gambarTopik, judulTopik, kategoriTopik, DeskripsiTopik, jumlahKomen, jumlahLike, jumlahReKuy;

    public DataTopik() {

    }

    public DataTopik(String idTopik, String gambarTopik, String judulTopik, String kategoriTopik, String deskripsiTopik, String jumlahKomen, String jumlahLike, String jumlahReKuy) {
        this.idTopik = idTopik;
        this.gambarTopik = gambarTopik;
        this.judulTopik = judulTopik;
        this.kategoriTopik = kategoriTopik;
        DeskripsiTopik = deskripsiTopik;
        this.jumlahKomen = jumlahKomen;
        this.jumlahLike = jumlahLike;
        this.jumlahReKuy = jumlahReKuy;
    }

    public String getIdTopik() {
        return idTopik;
    }

    public void setIdTopik(String idTopik) {
        this.idTopik = idTopik;
    }

    public String getGambarTopik() {
        return gambarTopik;
    }

    public void setGambarTopik(String gambarTopik) {
        this.gambarTopik = gambarTopik;
    }

    public String getJudulTopik() {
        return judulTopik;
    }

    public void setJudulTopik(String judulTopik) {
        this.judulTopik = judulTopik;
    }

    public String getKategoriTopik() {
        return kategoriTopik;
    }

    public void setKategoriTopik(String kategoriTopik) {
        this.kategoriTopik = kategoriTopik;
    }

    public String getDeskripsiTopik() {
        return DeskripsiTopik;
    }

    public void setDeskripsiTopik(String deskripsiTopik) {
        DeskripsiTopik = deskripsiTopik;
    }

    public String getJumlahKomen() {
        return jumlahKomen;
    }

    public void setJumlahKomen(String jumlahKomen) {
        this.jumlahKomen = jumlahKomen;
    }

    public String getJumlahLike() {
        return jumlahLike;
    }

    public void setJumlahLike(String jumlahLike) {
        this.jumlahLike = jumlahLike;
    }

    public String getJumlahReKuy() {
        return jumlahReKuy;
    }

    public void setJumlahReKuy(String jumlahReKuy) {
        this.jumlahReKuy = jumlahReKuy;
    }
}
