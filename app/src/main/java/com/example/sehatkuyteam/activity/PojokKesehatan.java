package com.example.sehatkuyteam.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.sehatkuyteam.R;
import com.example.sehatkuyteam.adapter.AdapterPojokKesehatan;
import com.example.sehatkuyteam.adapter.AdapterTopik;
import com.example.sehatkuyteam.addactivity.AddPojokKesehatan;
import com.example.sehatkuyteam.addactivity.AddTopik;
import com.example.sehatkuyteam.dataactivity.DataPojokKesehatan;
import com.example.sehatkuyteam.dataactivity.DataTopik;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PojokKesehatan extends AppCompatActivity {

    private SearchView sv;
    private ImageView ivAddInformasi, ivHome, ivDokter, ivArtikel, ivProfil;;
    private RecyclerView rvKuy;
    private List<DataPojokKesehatan> listPojokKesehatan;
    private DatabaseReference dbReference;
    private AdapterPojokKesehatan adapterPojokKesehatan;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pojok_kesehatan);

        rvKuy = findViewById(R.id.rv_pojok_kesehatan);
        sv = findViewById(R.id.sv_search);
        ivAddInformasi = findViewById(R.id.iv_add_informasi);
        ivHome = findViewById(R.id.iv_home);
        ivDokter = findViewById(R.id.iv_dokter);
        ivArtikel = findViewById(R.id.iv_artikel);
        ivProfil = findViewById(R.id.iv_profil);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PojokKesehatan.this, Diskusi.class));
                finish();
            }
        });

        ivDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PojokKesehatan.this, Dokter.class));
                finish();
            }
        });

        ivArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PojokKesehatan.this, PojokKesehatan.class));
                finish();
            }
        });

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PojokKesehatan.this, Profil.class));
                finish();
            }
        });

        sv.clearFocus();

        LinearLayoutManager llm = new LinearLayoutManager(PojokKesehatan.this);
        rvKuy.setLayoutManager(llm);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PojokKesehatan.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(R.layout.progress_layout);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        listPojokKesehatan = new ArrayList<>();

        adapterPojokKesehatan = new AdapterPojokKesehatan(PojokKesehatan.this, listPojokKesehatan);
        rvKuy.setAdapter(adapterPojokKesehatan);

        dbReference = FirebaseDatabase.getInstance().getReference("Pojok Kesehatan");
        alertDialog.show();

        valueEventListener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listPojokKesehatan.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    DataPojokKesehatan dataPojokKesehatan = itemSnapshot.getValue(DataPojokKesehatan.class);
                    listPojokKesehatan.add(dataPojokKesehatan);
                }
                adapterPojokKesehatan.notifyDataSetChanged();
                alertDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                alertDialog.dismiss();
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchList(newText);
                return true;
            }
        });

        ivAddInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PojokKesehatan.this, AddPojokKesehatan.class));
            }
        });
    }

    public void SearchList(String text) {
        ArrayList<DataPojokKesehatan> searchList = new ArrayList<>();
        for (DataPojokKesehatan dataPojokKesehatan: listPojokKesehatan) {
            if (dataPojokKesehatan.getJudulTopik().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataPojokKesehatan);
            }
        }
        adapterPojokKesehatan.SearchDataList(searchList);
    }
}