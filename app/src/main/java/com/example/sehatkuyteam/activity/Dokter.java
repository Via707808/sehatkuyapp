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
import com.example.sehatkuyteam.adapter.AdapterDiskusi;
import com.example.sehatkuyteam.adapter.AdapterDokter;
import com.example.sehatkuyteam.addactivity.AddDiskusi;
import com.example.sehatkuyteam.addactivity.AddDokter;
import com.example.sehatkuyteam.dataactivity.DataDiskusi;
import com.example.sehatkuyteam.dataactivity.DataDokter;
import com.example.sehatkuyteam.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dokter extends AppCompatActivity {

    private SearchView sv;
    private ImageView ivObat, ivHome, ivDokter, ivArtikel, ivProfil, ivAddObat;
    private RecyclerView rvKuy;
    private List<DataDokter> listDiskusi;
    private DatabaseReference dbReference;
    private AdapterDokter adapterDiskusi;
    private ActivityMainBinding binding;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        rvKuy = findViewById(R.id.rv_dokter);
        ivHome = findViewById(R.id.iv_home);
        ivDokter = findViewById(R.id.iv_dokter);
        ivArtikel = findViewById(R.id.iv_artikel);
        ivProfil = findViewById(R.id.iv_profil);
        ivObat = findViewById(R.id.iv_topik);
        ivAddObat = findViewById(R.id.iv_add_dokter);
        sv = findViewById(R.id.sv_search_dokter);

        sv.clearFocus();

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dokter.this, Diskusi.class));
                finish();
            }
        });

        ivArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dokter.this, PojokKesehatan.class));
                finish();
            }
        });

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dokter.this, Profil.class));
                finish();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(Dokter.this);
        rvKuy.setLayoutManager(llm);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Dokter.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(R.layout.progress_layout);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        listDiskusi = new ArrayList<>();

        adapterDiskusi = new AdapterDokter(Dokter.this, listDiskusi);
        rvKuy.setAdapter(adapterDiskusi);

        dbReference = FirebaseDatabase.getInstance().getReference("Dokter");
        alertDialog.show();

        valueEventListener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDiskusi.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    DataDokter dataDiskusi = itemSnapshot.getValue(DataDokter.class);
                    listDiskusi.add(dataDiskusi);
                }
                adapterDiskusi.notifyDataSetChanged();
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

        ivObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dokter.this, Obat.class));
            }
        });

        ivAddObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dokter.this, AddDokter.class));
            }
        });
    }

    public void SearchList(String text) {
        ArrayList<DataDokter> searchList = new ArrayList<>();
        for (DataDokter dataDiskusi: listDiskusi) {
            if (dataDiskusi.getNamaDokter().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataDiskusi);
            }
        }
        adapterDiskusi.SearchDataList(searchList);
    }
}