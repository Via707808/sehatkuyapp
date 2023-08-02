package com.example.sehatkuyteam.dataactivity;

public class DataDokter {
    String idDokter, gambarDokter, namaDokter, spesialisDokter, biayaDokter, nomorSTR, komentarDokter;

    public DataDokter() {

    }

    public DataDokter(String idDokter, String gambarDOkter, String namaDokter, String spesialisDokter, String biayaDokter, String nomorSTR, String komentarDokter) {
        this.idDokter = idDokter;
        this.gambarDokter = gambarDokter;
        this.namaDokter = namaDokter;
        this.spesialisDokter = spesialisDokter;
        this.biayaDokter = biayaDokter;
        this.nomorSTR = nomorSTR;
        this.komentarDokter = komentarDokter;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getGambarDokter() {
        return gambarDokter;
    }

    public void setGambarDokter(String gambarDokter) {
        this.gambarDokter = gambarDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getSpesialisDokter() {
        return spesialisDokter;
    }

    public void setSpesialisDokter(String spesialisDokter) {
        this.spesialisDokter = spesialisDokter;
    }

    public String getBiayaDokter() {
        return biayaDokter;
    }

    public void setBiayaDokter(String biayaDokter) {
        this.biayaDokter = biayaDokter;
    }

    public String getNomorSTR() {
        return nomorSTR;
    }

    public void setNomorSTR(String nomorSTR) {
        this.nomorSTR = nomorSTR;
    }

    public String getKomentarDokter() {
        return komentarDokter;
    }

    public void setKomentarDokter(String komentarDokter) {
        this.komentarDokter = komentarDokter;
    }
}
