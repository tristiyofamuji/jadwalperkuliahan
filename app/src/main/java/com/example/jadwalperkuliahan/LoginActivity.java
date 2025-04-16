package com.example.jadwalperkuliahan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;

    // Ganti ini dengan IP server kamu
    private static final String URL_LOGIN = "http://10.10.10.27/jadwalkuliah/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String nim = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (nim.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "NIM dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                login(nim, password);
            }
        });
    }

    private void login(String nim, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean status = jsonObject.getBoolean("status");
                        String message = jsonObject.getString("message");

                        if (status) {
                            JSONObject data = jsonObject.getJSONObject("data");
                            String nama = data.getString("nama_lengkap");

                            Toast.makeText(LoginActivity.this, "Selamat datang, " + nama, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Kesalahan parsing data", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(LoginActivity.this, "Terjadi kesalahan koneksi", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nim", nim);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
