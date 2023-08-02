package com.example.sehatkuyteam.addactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sehatkuyteam.R;
import com.example.sehatkuyteam.dataactivity.DataDiskusi;
import com.example.sehatkuyteam.dataactivity.DataObat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddObat extends AppCompatActivity {

    private ImageView ivPreviewPhoto, ivAddPhoto;
    private EditText etNamaObat, etHargaPer, etHarga, etDiskon, etKategori, etDibeli;
    private Button btnKirim;
    private String imageUrl;
    private Uri uri;
    private SharedPreferences sp;
    private DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("Obat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_obat);

        sp = getSharedPreferences("pengguna", MODE_PRIVATE);

        ivPreviewPhoto = findViewById(R.id.iv_preview_obat);
        ivAddPhoto = findViewById(R.id.iv_add_image);
        etNamaObat = findViewById(R.id.et_namaobat);
        etHargaPer = findViewById(R.id.et_namaobat);
        etHarga = findViewById(R.id.et_namaobat);
        etDiskon = findViewById(R.id.et_namaobat);
        etKategori = findViewById(R.id.et_namaobat);
        etDibeli = findViewById(R.id.et_namaobat);
        btnKirim = findViewById(R.id.btn_kirim);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    uri = data.getData();
                    ivPreviewPhoto.setImageURI(uri);
                    ivPreviewPhoto.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(AddObat.this, "Tidak ada gambar yang dipilih", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
    }

    public void SaveData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Obat Image").child(uri.getLastPathSegment());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddObat.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(R.layout.progress_layout);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                UploadData();
                alertDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                alertDialog.dismiss();
            }
        });
    }

    public void UploadData() {
        String namaObat = etNamaObat.getText().toString();
        String hargaPer = etHargaPer.getText().toString();
        String harga = etHarga.getText().toString();
        String kategori = etKategori.getText().toString();
        String diskon = etDiskon.getText().toString();
        String dibeli = etDibeli.getText().toString();

        DataObat dataDiskusi = new DataObat();
        dataDiskusi.setNamaObat(namaObat);
        dataDiskusi.setHargaPer(hargaPer);
        dataDiskusi.setHarga(harga);
        dataDiskusi.setKategori(kategori);
        dataDiskusi.setDiskon(diskon);
        dataDiskusi.setDibeli(dibeli);
        dataDiskusi.setGambarObat(imageUrl);

        // Generate ID
        String idDiskusi = dbReference.push().getKey();
        dataDiskusi.setIdObat(idDiskusi);

        dbReference.child(idDiskusi).setValue(dataDiskusi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddObat.this, "Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddObat.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}