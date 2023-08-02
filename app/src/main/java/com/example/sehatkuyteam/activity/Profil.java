package com.example.sehatkuyteam.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sehatkuyteam.R;

public class Profil extends AppCompatActivity {

    private ImageView ivHome, ivDokter, ivArtikel, ivProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ivHome = findViewById(R.id.iv_home);
        ivDokter = findViewById(R.id.iv_dokter);
        ivArtikel = findViewById(R.id.iv_artikel);
        ivProfil = findViewById(R.id.iv_profil);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profil.this, Diskusi.class));
                finish();
            }
        });

        ivDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profil.this, Dokter.class));
                finish();
            }
        });

        ivArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profil.this, PojokKesehatan.class));
                finish();
            }
        });

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profil.this, Profil.class));
                finish();
            }
        });
    }
}