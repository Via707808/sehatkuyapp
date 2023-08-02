package com.example.sehatkuyteam.dataactivity;

public class DataDiskusi {
    String idDiskusi, username, textKuy, imageKuy, waktu, jumlahKomen, jumlahLike, jumlahReKuy;

    public DataDiskusi() {

    }

    public DataDiskusi(String idDiskusi, String username, String textKuy, String imageKuy, String waktu, String jumlahKomen, String jumlahLike, String jumlahReKuy) {
        this.idDiskusi = idDiskusi;
        this.username = username;
        this.textKuy = textKuy;
        this.imageKuy = imageKuy;
        this.waktu = waktu;
        this.jumlahKomen = jumlahKomen;
        this.jumlahLike = jumlahLike;
        this.jumlahReKuy = jumlahReKuy;
    }

    public String getIdDiskusi() {
        return idDiskusi;
    }

    public void setIdDiskusi(String idDiskusi) {
        this.idDiskusi = idDiskusi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTextKuy() {
        return textKuy;
    }

    public void setTextKuy(String textKuy) {
        this.textKuy = textKuy;
    }

    public String getImageKuy() {
        return imageKuy;
    }

    public void setImageKuy(String imageKuy) {
        this.imageKuy = imageKuy;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
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
