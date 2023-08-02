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

import com.example.sehatkuyteam.adapter.AdapterDiskusi;
import com.example.sehatkuyteam.R;
import com.example.sehatkuyteam.addactivity.AddDiskusi;
import com.example.sehatkuyteam.dataactivity.DataDiskusi;
import com.example.sehatkuyteam.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Diskusi extends AppCompatActivity {

    private SearchView sv;
    private ImageView ivTopik, ivHome, ivDokter, ivArtikel, ivProfil;
    private RecyclerView rvKuy;
    private List<DataDiskusi> listDiskusi;
    private FloatingActionButton fabAddDiskusi;
    private DatabaseReference dbReference;
    private AdapterDiskusi adapterDiskusi;
    private ActivityMainBinding binding;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diskusi);

        rvKuy = findViewById(R.id.rv_kuy);
        ivHome = findViewById(R.id.iv_home);
        ivDokter = findViewById(R.id.iv_dokter);
        ivArtikel = findViewById(R.id.iv_artikel);
        ivProfil = findViewById(R.id.iv_profil);
        ivTopik = findViewById(R.id.iv_topik);
        sv = findViewById(R.id.sv_search_diskusi);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        fabAddDiskusi = findViewById(R.id.fab_add_diskusi);

        sv.clearFocus();

        LinearLayoutManager llm = new LinearLayoutManager(Diskusi.this);
        rvKuy.setLayoutManager(llm);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Diskusi.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(R.layout.progress_layout);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        listDiskusi = new ArrayList<>();

        adapterDiskusi = new AdapterDiskusi(Diskusi.this, listDiskusi);
        rvKuy.setAdapter(adapterDiskusi);

        dbReference = FirebaseDatabase.getInstance().getReference("Diskusi");
        alertDialog.show();

        valueEventListener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDiskusi.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    DataDiskusi dataDiskusi = itemSnapshot.getValue(DataDiskusi.class);
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

        ivTopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diskusi.this, Topik.class));
            }
        });

        fabAddDiskusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diskusi.this, AddDiskusi.class));
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diskusi.this, Diskusi.class));
                finish();
            }
        });

        ivDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diskusi.this, Dokter.class));
                finish();
            }
        });

        ivArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diskusi.this, PojokKesehatan.class));
                finish();
            }
        });

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diskusi.this, Profil.class));
                finish();
            }
        });


    }

    public void SearchList(String text) {
        ArrayList<DataDiskusi> searchList = new ArrayList<>();
        for (DataDiskusi dataDiskusi: listDiskusi) {
            if (dataDiskusi.getUsername().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataDiskusi);
            }
        }
        adapterDiskusi.SearchDataList(searchList);
    }
}