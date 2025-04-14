package com.example.jadwalperkuliahan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cek apakah login berhasil (misalnya cek username dan password)
                if (isLoginValid(edtUsername.getText().toString(), edtPassword.getText().toString())) {
                    // Pindah ke MainActivity jika login berhasil
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Menutup LoginActivity agar tidak bisa kembali dengan tombol back
                } else {
                    // Tampilkan pesan kesalahan login
                    Toast.makeText(LoginActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isLoginValid(String username, String password) {
        // Gantilah dengan logika autentikasi yang sesuai, misalnya mengecek username dan password
        return "123456".equals(username) && "123456".equals(password); // Contoh sederhana
    }
}
