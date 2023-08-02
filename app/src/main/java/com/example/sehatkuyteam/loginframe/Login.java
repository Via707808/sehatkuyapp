package com.example.sehatkuyteam.loginframe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sehatkuyteam.activity.Diskusi;
import com.example.sehatkuyteam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private CheckBox cbRememberMe;
    private TextView tvSignUp;
    private Button btnLogin;
    private SharedPreferences sp, spA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("pengguna", MODE_PRIVATE);
        spA = getSharedPreferences("penggunarememberme", MODE_PRIVATE);

        etUsername = findViewById( R.id.et_login_username);
        etPassword = findViewById( R.id.et_login_password);
        cbRememberMe = findViewById( R.id.cb_login);
        tvSignUp = findViewById( R.id.tv_login_signup);
        btnLogin = findViewById( R.id.btn_login_login);

        if (spA.getString("username", null) != null) {
            etUsername.setText(spA.getString("username", null));
            etPassword.setText(spA.getString("password", null));
            startActivity(new Intent(Login.this, Diskusi.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }

    public void checkUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.trim().isEmpty()) {
            etUsername.setError("Username tidak boleh kosong");
        } if (password.trim().isEmpty()) {
            etUsername.setError("Password tidak boleh kosong");
        } else {
            DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUserDatabase = dbReference.orderByChild("username").equalTo(username);

            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        etUsername.setError(null);
                        String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);

                        if (passwordFromDB.equals(password)) {
                            if (cbRememberMe.isChecked()) {
                                SharedPreferences.Editor spEditorA = spA.edit();
                                spEditorA.putString("username", etUsername.getText().toString());
                                spEditorA.putString("password", etPassword.getText().toString());
                                spEditorA.apply();
                            }
                            SharedPreferences.Editor spEditor = sp.edit();
                            spEditor.putString("username", etUsername.getText().toString());
                            spEditor.putString("password", etPassword.getText().toString());
                            spEditor.apply();
                            startActivity(new Intent(Login.this, Diskusi.class));
                            finish();
                        } else {
                            etPassword.setError("Password salah");
                            etPassword.requestFocus();
                        }
                    } else {
                        etUsername.setError("User tidak terdaftar");
                        etUsername.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}