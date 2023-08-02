package com.example.sehatkuyteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sehatkuyteam.activity.DetailChat;

public class DetailDokter extends AppCompatActivity {

    private Button btnChatDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        btnChatDokter = findViewById(R.id.buttonchat);

        btnChatDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailDokter.this, DetailChat.class));
                finish();
            }
        });
    }
}