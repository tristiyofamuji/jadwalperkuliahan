package com.example.jadwalperkuliahan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewSchedule;
    ArrayList<Schedule> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Memeriksa apakah user sudah login
        if (!isUserLoggedIn()) {
            // Jika belum login, buka LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Menutup MainActivity agar hanya LoginActivity yang muncul
        }

        setContentView(R.layout.activity_main);

        listViewSchedule = findViewById(R.id.listViewSchedule);
        scheduleList = new ArrayList<>();

        // Menambahkan data jadwal perkuliahan
        scheduleList.add(new Schedule("Pemrograman Website", "R. Bagus Bambang Sumantri", "Tanggal: 20/04/2025 | Waktu: 10:00 - 12:00"));
        scheduleList.add(new Schedule("Kalkulus", "Dede Yusuf", "Tanggal: 21/04/2025 | Waktu: 08:00 - 10:00"));
        scheduleList.add(new Schedule("Algoritma & Struktur Data", "Walidy Rahman Hakim", "Tanggal: 22/04/2025 | Waktu: 13:00 - 15:00"));
        scheduleList.add(new Schedule("Basis Data", "Tri Stiyo Famuji", "Tanggal: 23/04/2025 | Waktu: 09:00 - 11:00"));

        // Menampilkan data dalam ListView
        ScheduleAdapter adapter = new ScheduleAdapter(this, scheduleList);
        listViewSchedule.setAdapter(adapter);
    }

    // Cek apakah user sudah login
    private boolean isUserLoggedIn() {
        // Anda dapat menyimpan status login di SharedPreferences atau menggunakan metode lain
        // Contoh menggunakan SharedPreferences (pastikan sudah di-setup sebelumnya)
        // SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        // return sharedPreferences.getBoolean("isLoggedIn", false);

        // Untuk contoh ini, anggap login berhasil jika login activity sudah dilalui
        return true; // Ubah menjadi kondisi login yang sebenarnya
    }

    // Method ini akan dipanggil dari tombol "Absen" pada item list (via XML onClick)
    public void onAbsenClick(View view) {
        int position = (int) view.getTag();
        Schedule selectedSchedule = scheduleList.get(position);
        Toast.makeText(this, "Absen untuk " + selectedSchedule.getSubject() + " berhasil!", Toast.LENGTH_SHORT).show();
    }
}
