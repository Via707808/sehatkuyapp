package com.example.sehatkuyteam.loginframe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sehatkuyteam.dataactivity.DataUser;
import com.example.sehatkuyteam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class SignUp extends AppCompatActivity {

    private EditText etName, etUsername, etEmail, etPassword, etConfirmPassword;
    private CheckBox cbPrivacyPolicy;
    private Button btnSignUp;
    private TextView tvLogin;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById( R.id.et_signup_name);
        etUsername = findViewById( R.id.et_signup_username);
        etEmail = findViewById( R.id.et_signup_email);
        etPassword = findViewById( R.id.et_signup_password);
        etConfirmPassword = findViewById( R.id.et_signup_confirmpassword);
        tvLogin = findViewById( R.id.tv_signup_login);
        cbPrivacyPolicy = findViewById( R.id.cb_signup);
        btnSignUp = findViewById( R.id.btn_signup_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                dbReference = firebaseDatabase.getReference("users");

                String idUser = UUID.randomUUID().toString();
                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (name.trim().isEmpty()) {
                    etName.setError("Nama tidak boleh kosong");
                } if (username.trim().isEmpty()) {
                    etUsername.setError("Username tidak boleh kosong");
                } if (email.trim().isEmpty()) {
                    etEmail.setError("Email tidak boleh kosong");
                } if (password.trim().isEmpty()) {
                    etUsername.setError("Password tidak boleh kosong");
                } else {
                    DataUser dataUser = new DataUser(idUser, name, username, email, password);
                    dbReference.child(username).setValue(dataUser);

                    Toast.makeText(SignUp.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this, Login.class));
                    finish();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });
    }
}