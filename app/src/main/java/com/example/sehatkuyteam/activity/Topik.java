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
import com.example.sehatkuyteam.adapter.AdapterTopik;
import com.example.sehatkuyteam.addactivity.AddDiskusi;
import com.example.sehatkuyteam.addactivity.AddTopik;
import com.example.sehatkuyteam.dataactivity.DataDiskusi;
import com.example.sehatkuyteam.dataactivity.DataTopik;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Topik extends AppCompatActivity {

    private SearchView sv;
    private ImageView ivDiskusi, ivHome, ivDokter, ivArtikel, ivProfil;
    private RecyclerView rvKuy;
    private List<DataTopik> listTopik;
    private FloatingActionButton fabAddTopik;
    private DatabaseReference dbReference;
    private AdapterTopik adapterTopik;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topik);

        rvKuy = findViewById(R.id.rv_topik);
        sv = findViewById(R.id.sv_search_topik);
        ivDiskusi = findViewById(R.id.iv_diskusi);
        fabAddTopik = findViewById(R.id.fab_add_topik);
        ivHome = findViewById(R.id.iv_home);
        ivDokter = findViewById(R.id.iv_dokter);
        ivArtikel = findViewById(R.id.iv_artikel);
        ivProfil = findViewById(R.id.iv_profil);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Topik.this, Diskusi.class));
                finish();
            }
        });

        ivDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Topik.this, Dokter.class));
                finish();
            }
        });

        ivArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Topik.this, PojokKesehatan.class));
                finish();
            }
        });

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Topik.this, Profil.class));
                finish();
            }
        });

        sv.clearFocus();

        LinearLayoutManager llm = new LinearLayoutManager(Topik.this);
        rvKuy.setLayoutManager(llm);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Topik.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(R.layout.progress_layout);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        listTopik = new ArrayList<>();

        adapterTopik = new AdapterTopik(Topik.this, listTopik);
        rvKuy.setAdapter(adapterTopik);

        dbReference = FirebaseDatabase.getInstance().getReference("Topik");
        alertDialog.show();

        valueEventListener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTopik.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    DataTopik dataTopik = itemSnapshot.getValue(DataTopik.class);
                    listTopik.add(dataTopik);
                }
                adapterTopik.notifyDataSetChanged();
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

        ivDiskusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Topik.this, Diskusi.class));
            }
        });

        fabAddTopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Topik.this, AddTopik.class));
            }
        });
    }

    public void SearchList(String text) {
        ArrayList<DataTopik> searchList = new ArrayList<>();
        for (DataTopik dataTopik: listTopik) {
            if (dataTopik.getJudulTopik().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataTopik);
            }
        }
        adapterTopik.SearchDataList(searchList);
    }
}